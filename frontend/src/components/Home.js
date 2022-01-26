import {Button, Stack, Typography} from "@mui/material";
import {Link} from "react-router-dom";

function Home() {

    return (
        <Stack direction="column" alignItems="center" spacing={2} mt={4}>
            <Button component={Link} to="/register" variant="contained">Zarejestruj się</Button>
            <Button component={Link} to="/login" variant="contained">Zaloguj się</Button>
        </Stack>
    )
}

export default Home