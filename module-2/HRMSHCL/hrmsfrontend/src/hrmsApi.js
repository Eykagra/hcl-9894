const baseUrl = import.meta.env.VITE_HRMS_API_URL || 'http://localhost:8080/v1/user'

export async function hrmsRequest(path, options = {}) {
  let response
  try {
    response = await fetch(`${baseUrl}${path}`, {
      ...options,
      headers: options.body ? { 'Content-Type': 'application/json' } : undefined,
    })
  } catch (error) {
    if (options.signal?.aborted) throw error
    throw new Error('Could not connect to the HRMS backend.', { cause: error })
  }
  if (!response.ok) {
    const error = await response.json().catch(() => ({}))
    throw new Error(error.detail || error.message || `Request failed (${response.status}).`)
  }
  return response
}
