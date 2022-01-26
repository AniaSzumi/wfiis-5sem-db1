import {Button} from "@mui/material";
import { useNavigate } from "react-router-dom";

function LogoutButton() {
    let navigate = useNavigate();

    const logout = () => {
        sessionStorage.removeItem("userId")
        sessionStorage.removeItem("userRole")
        navigate("/")
    }

    return (
        <Button variant="contained" onClick={logout}>Wyloguj się</Button>
    )
}

export default LogoutButton