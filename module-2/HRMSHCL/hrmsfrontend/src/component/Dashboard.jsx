import { useCallback, useEffect, useState } from 'react'
import { Link } from 'react-router-dom'
import { hrmsRequest } from '../hrmsApi'
import './attendance.css'

const timeFormat = new Intl.DateTimeFormat(undefined, { hour: 'numeric', minute: '2-digit' })
const dayFormat = new Intl.DateTimeFormat(undefined, { dateStyle: 'full' })

function formatTime(value) {
  return value ? timeFormat.format(new Date(value)) : '—'
}

function hoursWorked(entry) {
  if (!entry.inTime || !entry.outTime) return '—'
  const minutes = Math.floor((new Date(entry.outTime) - new Date(entry.inTime)) / 60000)
  return minutes >= 0 ? `${Math.floor(minutes / 60)}h ${minutes % 60}m` : '—'
}

export default function Dashboard() {
  const [now, setNow] = useState(() => new Date())
  const [entries, setEntries] = useState([])
  const [search, setSearch] = useState('')
  const [filter, setFilter] = useState('all')
  const [notice, setNotice] = useState('')
  const [loadError, setLoadError] = useState('')
  const [actionError, setActionError] = useState('')
  const [loading, setLoading] = useState(true)
  const [pending, setPending] = useState(false)

  const refresh = useCallback(async (signal) => {
    setLoading(true)
    setLoadError('')
    try {
      const response = await hrmsRequest('/attendance/today', { signal })
      const records = await response.json()
      if (!signal?.aborted) setEntries(records)
    } catch (error) {
      if (!signal?.aborted) setLoadError(error.message)
    } finally {
      if (!signal?.aborted) setLoading(false)
    }
  }, [])

  const dayKey = now.toDateString()
  useEffect(() => {
    const controller = new AbortController()
    const load = window.setTimeout(() => refresh(controller.signal), 0)
    return () => {
      window.clearTimeout(load)
      controller.abort()
    }
  }, [refresh, dayKey])

  useEffect(() => {
    const clock = setInterval(() => setNow(new Date()), 1000)
    return () => clearInterval(clock)
  }, [])

  async function updateAttendance(path, options, message) {
    setPending(true)
    setNotice('')
    setActionError('')
    try {
      await hrmsRequest(path, options)
      setNotice(message)
      await refresh()
      return true
    } catch (error) {
      setActionError(error.message)
      return false
    } finally {
      setPending(false)
    }
  }

  async function markAttendance(event) {
    event.preventDefault()
    const form = event.currentTarget
    const data = new FormData(form)
    const marked = await updateAttendance('/mark', {
      method: 'POST',
      body: JSON.stringify({ empId: data.get('empId'), comment: data.get('comment').trim() }),
    }, 'Attendance marked.')
    if (marked) form.reset()
  }

  const checkedOut = entries.filter(entry => entry.outTime).length
  const inOffice = entries.length - checkedOut
  const query = search.trim().toLowerCase()
  const visibleEntries = entries.filter(entry => {
    const status = entry.outTime ? 'out' : 'in'
    const matchesSearch = [entry.empId, entry.empName, entry.designation]
      .some(value => String(value ?? '').toLowerCase().includes(query))
    return matchesSearch && (filter === 'all' || filter === status)
  })

  return (
    <div className="attendance-shell">
      <header className="attendance-nav">
        <Link className="attendance-brand" to="/dashboard">HRMS</Link>
        <nav aria-label="HRMS">
          <Link to="/dashboard" aria-current="page">Attendance</Link>
          <Link to="/register">Register employee</Link>
        </nav>
      </header>

      <main className="attendance-main">
        <div className="attendance-heading">
          <div><p>{dayFormat.format(now)}</p><h1>Attendance</h1></div>
          <time dateTime={now.toISOString()}>{timeFormat.format(now)}</time>
        </div>

        <dl className="attendance-stats" aria-label="Daily attendance summary">
          <div><dt>Marked present</dt><dd>{loadError ? '—' : entries.length}</dd></div>
          <div><dt>In office</dt><dd>{loadError ? '—' : inOffice}</dd></div>
          <div><dt>Checked out</dt><dd>{loadError ? '—' : checkedOut}</dd></div>
        </dl>

        <section className="attendance-panel" aria-labelledby="mark-title">
          <h2 id="mark-title">Mark attendance</h2>
          <form className="attendance-form" onSubmit={markAttendance}>
            <label>Employee ID<input name="empId" inputMode="numeric" pattern="[0-9]+" required /></label>
            <label><span>Comment <small>(optional)</small></span><input name="comment" maxLength={255} /></label>
            <button className="attendance-primary" disabled={pending || loading}>Mark attendance</button>
          </form>
        </section>

        <section className="attendance-panel" aria-labelledby="roster-title">
          {notice && <p className="attendance-notice" role="status">{notice}</p>}
          {actionError && <p className="attendance-error" role="alert">{actionError}</p>}
          <div className="attendance-toolbar">
            <h2 id="roster-title">Today's attendance</h2>
            <div className="attendance-filters">
              <input type="search" aria-label="Search employee ID, name or designation" placeholder="Search employees" value={search} onChange={event => setSearch(event.target.value)} />
              <select aria-label="Attendance status" value={filter} onChange={event => setFilter(event.target.value)}>
                <option value="all">All statuses</option>
                <option value="in">In office</option>
                <option value="out">Checked out</option>
              </select>
              <button type="button" onClick={() => refresh()} disabled={loading || pending}>Refresh</button>
            </div>
          </div>
          {loadError && <p className="attendance-error" role="alert">{loadError}</p>}
          <div className="attendance-table" role="region" aria-label="Attendance records" tabIndex={0}>
            <table>
              <thead><tr>
                <th scope="col">Employee</th><th scope="col">Designation</th><th scope="col">In</th><th scope="col">Out</th>
                <th scope="col">Hours</th><th scope="col">Status</th><th scope="col">Comment</th><th scope="col">Action</th>
              </tr></thead>
              <tbody>
                {visibleEntries.map(entry => (
                  <tr key={entry.attendanceId}>
                    <th scope="row">{entry.empName || `Employee ${entry.empId}`}<small>ID {entry.empId}</small></th>
                    <td>{entry.designation || '—'}</td>
                    <td>{formatTime(entry.inTime)}</td><td>{formatTime(entry.outTime)}</td><td>{hoursWorked(entry)}</td>
                    <td><span className={entry.outTime ? 'attendance-pill' : 'attendance-pill present'}>{entry.outTime ? 'Checked out' : 'In office'}</span></td>
                    <td className="attendance-comment">{entry.comment || '—'}</td>
                    <td>{entry.outTime ? '—' : (
                      <button type="button" disabled={pending || loading} aria-label={`Check out ${entry.empName || entry.empId}`}
                        onClick={() => updateAttendance(`/attendance/${entry.attendanceId}/checkout`, { method: 'PATCH' }, 'Employee checked out.')}>
                        Check out
                      </button>
                    )}</td>
                  </tr>
                ))}
                {!visibleEntries.length && (
                  <tr><td colSpan={8} className="attendance-empty">
                    {loading ? 'Loading attendance…' : loadError ? 'Attendance is unavailable.' : entries.length ? 'No employees match the current filters.' : 'No attendance has been marked today.'}
                  </td></tr>
                )}
              </tbody>
            </table>
          </div>
        </section>
      </main>
    </div>
  )
}
