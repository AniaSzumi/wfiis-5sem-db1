import {Button, FormControl, InputLabel, MenuItem, Select, Stack, TextField, Typography} from "@mui/material";
import {useEffect, useState} from "react";
// import {useDebounce} from "./useDebounce";
import { useNavigate } from "react-router-dom";

function Login({ setUser, userRole, setUserRole }) {
    const [email, setEmail] = useState("")
    const [password, setPassword] = useState("")
    const [foundUser, setFoundUser] = useState(true)
    let navigate = useNavigate();

    const login = () => {
            fetch("/api/" + userRole + "/login", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify({email, password})
            }).then((res) => res.json())
                .then((data) => {
                    if (Object.keys(data).length > 0) {
                        setUser(data)
                        setFoundUser(true)
                        sessionStorage.setItem("user", JSON.stringify(data))
                        sessionStorage.setItem("userRole", userRole)
                        navigate("/"+userRole);
                    } else {
                        setFoundUser(false)
                    }
                    window.location.reload()
                })
                .catch((error) => console.log(error))
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
                <MenuItem value="pracownik">Pracownik</MenuItem>
                <MenuItem value="manager">Manager</MenuItem>
            </Select>
        </FormControl>
        <TextField label="Email" value={email} onChange={(e) => setEmail(e.target.value)} />
        <TextField label="Hasło" value={password} onChange={(e) => setPassword(e.target.value)} type="password" />
        <Button variant="contained" onClick={login}>Zaloguj</Button>
        {!foundUser && <Typography variant="body" color="error">Nie ma takiego użytkownika</Typography> }
        </Stack>
    )
}

export default Login