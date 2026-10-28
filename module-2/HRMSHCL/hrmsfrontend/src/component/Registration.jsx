import { useState } from 'react'
import { Link } from 'react-router-dom'
import { hrmsRequest } from '../hrmsApi'
import './attendance.css'

export default function Registration() {
  const [pending, setPending] = useState(false)
  const [notice, setNotice] = useState('')
  const [error, setError] = useState('')

  async function register(event) {
    event.preventDefault()
    const form = event.currentTarget
    setPending(true)
    setNotice('')
    setError('')
    try {
      await hrmsRequest('/addemp', {
        method: 'POST',
        body: JSON.stringify(Object.fromEntries(new FormData(form))),
      })
      form.reset()
      setNotice('Employee registered. Use their ID to mark attendance.')
    } catch (failure) {
      setError(failure.message)
    } finally {
      setPending(false)
    }
  }

  return (
    <div className="attendance-shell">
      <header className="attendance-nav">
        <Link className="attendance-brand" to="/dashboard">HRMS</Link>
        <nav aria-label="HRMS">
          <Link to="/dashboard">Attendance</Link>
          <Link to="/register" aria-current="page">Register employee</Link>
        </nav>
      </header>
      <main className="attendance-main">
        <h1>Register employee</h1>
        <form className="attendance-panel attendance-registration" onSubmit={register}>
          {notice && <p className="attendance-notice" role="status">{notice}</p>}
          {error && <p className="attendance-error" role="alert">{error}</p>}
          <label>Employee ID<input name="empId" inputMode="numeric" pattern="[0-9]+" required /></label>
          <label>Name<input name="empName" required /></label>
          <label>Designation<input name="designation" required /></label>
          <label>Tech stack<input name="stack" required /></label>
          <label>Email<input name="email" type="email" required /></label>
          <label>Password<input name="password" type="password" autoComplete="new-password" required /></label>
          <button className="attendance-primary" disabled={pending}>Register employee</button>
        </form>
      </main>
    </div>
  )
}
