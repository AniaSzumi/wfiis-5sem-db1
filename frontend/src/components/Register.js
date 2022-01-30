import {Button, FormControl, InputLabel, MenuItem, Select, Stack, TextField, Typography} from "@mui/material";
import {useEffect, useState} from "react";
// import {useDebounce} from "./useDebounce";
import { useNavigate } from "react-router-dom";

function Register({ userRole, setUserRole }) {
    const [name, setName] = useState("")
    const [surname, setSurname] = useState("")
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [foundUser, setFoundUser] = useState(false)
    let navigate = useNavigate();

    const login = () => {
        let user = {
            name: name,
            surname: surname,
            email: email,
            password: password
        }
        fetch("/api/" + userRole + "/register", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(user)
        }).then((res) => res.json())
            .then((data) => {
                console.log(data)
                if (data === 0) {
                    setFoundUser(true)
                    reset()
                } else {
                    setFoundUser(false)
                    navigate("../login");
                }
            })
            .catch((error) => console.log(error))
    }

    const reset = () => {
        setName("")
        setSurname("")
        setEmail("")
        setPassword("")
    }

    return (
        <Stack direction="column" alignItems="center" spacing={2}>
            <Typography>Wybierz kim jesteś</Typography>
            <FormControl style={{minWidth: 200}}>
                <InputLabel id="role-label">Rola</InputLabel>
                <Select
                    labelId="role-label"
                    id="role"
                    value={userRole}
                    label="Rola"
                    onChange={(e) => setUserRole(e.target.value)}
                >
                    <MenuItem value="klient">Klient</MenuItem>
                    {/*<MenuItem value="pracownik">Pracownik</MenuItem>*/}
                    <MenuItem value="manager">Manager</MenuItem>
                </Select>
            </FormControl>
            <TextField label="Imię" value={name} onChange={(e) => setName(e.target.value)} />
            <TextField label="Nazwisko" value={surname} onChange={(e) => setSurname(e.target.value)} />
            <TextField label="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
            <TextField label="Hasło" value={password} onChange={(e) => setPassword(e.target.value)} type="password" />
            <Button variant="contained" onClick={login}>Zarejestruj się</Button>
            {foundUser && <Typography variant="body" color="error">Podany email jest zajęty</Typography> }
        </Stack>
    )
}

export default Register