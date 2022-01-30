import {Button, FormControl, InputLabel, MenuItem, Stack, TextField, Typography} from "@mui/material";
import {useEffect, useState} from "react";
// import {useDebounce} from "./useDebounce";
import { useNavigate } from "react-router-dom";
import Select from "./Select";


function AddWorker({ user }) {
    const [name, setName] = useState("")
    const [surname, setSurname] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [foundUser, setFoundUser] = useState(false)
    const [shops, setShops] = useState([])
    const [currentShop, setCurrentShop] = useState('')
    let navigate = useNavigate();

    useEffect(() => {
        fetch("/api/sklep/")
            .then((res) => res.json())
            .then((data) => setShops(data))
            .catch((err) => console.log(err))
    },[])

    const add = () => {
        if (name !== "" && surname !== "" && email !== "" && password !== "" && currentShop !== "") {
            let worker = {
                name: name,
                surname: surname,
                email: email,
                password: password,
                managerId: user.id,
                shopId: currentShop.charAt(0),
            }
            fetch("/api/pracownik/register", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(worker)
            }).then((res) => res.json())
                .then((data) => {
                    console.log(data)
                    if (data === 0) {
                        setFoundUser(true)
                        reset()
                    } else {
                        setFoundUser(false)
                        navigate("../manager");
                    }
                })
                .catch((error) => console.log(error))
        } else {
            alert("Pola nie mogą być puste")
        }
    }

    const reset = () => {
        setName("")
        setSurname("")
        setEmail("")
        setPassword("")
    }

    return (
        <Stack direction="column" alignItems="center" spacing={2} mt={4} >
            <Typography>Dodaj pracownika</Typography>
            <TextField label="Imię" value={name} onChange={(e) => setName(e.target.value)} />
            <TextField label="Nazwisko" value={surname} onChange={(e) => setSurname(e.target.value)} />
            <TextField label="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
            <TextField label="Hasło" value={password} onChange={(e) => setPassword(e.target.value)} type="password" />
            <Select label="Sklep" style={{maxWidth: "200px"}} items={shops} value={currentShop} onChange={setCurrentShop} />
            <Button variant="contained" onClick={add}>Dodaj pracownika</Button>
            {foundUser && <Typography variant="body" color="error">Podany email jest zajęty</Typography> }
        </Stack>
    )
}

export default AddWorker;