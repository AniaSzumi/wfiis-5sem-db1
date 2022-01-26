import {Button, List, ListItem, ListItemText, Stack, Typography} from "@mui/material";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import {useEffect, useState} from "react";
import LogoutButton from "./LogoutButton";
import PeopleList from "./PeopleList";
import {useNavigate} from "react-router-dom";

function Manager({ user }) {
    const [allWorkers, setAllWorkers] = useState([])
    const [myWorkers, setMyWorkers] = useState([])
    const [allShops, setAllShops] = useState([])
    const [myShops, setMyShops] = useState([])
    const navigate = useNavigate();

    useEffect(() => {
        fetch("/pracownik/")
            .then((res) => res.json())
            .then((data) => setAllWorkers(data))
            .catch((err) => console.log(err))
        fetch("/pracownik/manager/"+user.id)
            .then((res) => res.json())
            .then((data) => setMyWorkers(data))
            .catch((err) => console.log(err))
        fetch("/sklep/")
            .then((res) => res.json())
            .then((data) => setAllShops(data))
            .catch((err) => console.log(err))
        fetch("/sklep/manager/"+user.id)
            .then((res) => res.json())
            .then((data) => setMyShops(data))
            .catch((err) => console.log(err))
    },[])

    const addWorker = () => {
        navigate("/addWorker")
    }

    const addShop = () => {
        navigate("/addShop")
    }

    return (<>
        <center>
            <Typography variant="subtitle1" mt={2}>Manager: {user.imie+" "+user.nazwisko}</Typography>
            <LogoutButton style={{marginTop: 0}}/>
        </center>
        <Stack direction="row" justifyContent="space-evenly" mt={4}>
            <Stack direction="column" spacing={6} >
            <PeopleList title="Moi pracownicy"
                        people={myWorkers}
                        children={<Button onClick={addWorker} variant="contained" startIcon={<AddCircleOutlineIcon/>}>Dodaj pracownika</Button> }
            />
            <PeopleList title="Wszyscy pracownicy" people={allWorkers} />
            </Stack>
            <Stack direction="column" spacing={6}>
                <div>
                <Typography variant="h5">Moje sklepy</Typography>
                <List>
                    {myShops.map((shop) => (
                        <ListItem disableGutters key={shop}><ListItemText primary={shop} /></ListItem>
                    ))}
                </List>
                <Button onClick={addShop} variant="contained" startIcon={<AddCircleOutlineIcon/>}>Dodaj sklep</Button>
                </div>
                <div>
                <Typography variant="h5">Wszystkie sklepy</Typography>
                <List>
                    {allShops.map((shop) => (
                        <ListItem disableGutters key={shop}><ListItemText primary={shop} /></ListItem>
                    ))}
                </List>
                </div>
            </Stack>
        </Stack>
    </>)
};

export default Manager