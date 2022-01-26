import './App.css';
import Client from "./components/Client";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import Home from "./components/Home";
import Register from "./components/Register";
import Login from "./components/Login";
import {useEffect, useState} from "react";
import Manager from "./components/Manager";
import AddWorker from "./components/AddWorker";
import Worker from "./components/Worker";
import AddShop from "./components/AddShop";

function App() {
    const [user, setUser] = useState(JSON.parse(sessionStorage.getItem("user")))
    const [userRole, setUserRole] = useState(sessionStorage.getItem("userRole"))

    // useEffect(() => {
    //     console.log("Zmieniono id na:"+userId)
    // }, [userId])

  return (
      <BrowserRouter>
          <Routes>
              <Route path="/" element={<Home />} />
              <Route path="/klient" element={<Client user={user} />} />
              <Route path="/pracownik" element={<Worker user={user} />} />
              <Route path="/manager" element={<Manager user={user} />} />
              <Route path="/addWorker" element={<AddWorker user={user} />} />
              <Route path="/addShop" element={<AddShop user={user} />} />
              <Route path="/register" element={<Register userRole={userRole} setUserRole={setUserRole}/>} />
              <Route path="/login" element={<Login setUser={setUser} userRole={userRole} setUserRole={setUserRole} />} />
          </Routes>
      </BrowserRouter>
  )
}

export default App
