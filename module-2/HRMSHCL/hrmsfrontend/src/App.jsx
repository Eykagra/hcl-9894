import './App.css'
import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Login from './component/Login'
import Registration from './component/Registration'
import Dashboard from './component/Dashboard'

function App() {



  return (
    <div>
     <BrowserRouter>

     <Routes>
    <Route path='/' element={<Dashboard />}></Route>
    <Route path='/login' element={<Login />}></Route>
    <Route path='/register' element={<Registration />}></Route>
    <Route path='/dashboard' element={<Dashboard />} />

     </Routes>
     </BrowserRouter>



    </div>
  )
}

export default App
