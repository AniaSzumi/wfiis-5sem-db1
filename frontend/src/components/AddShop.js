import {Button, FormControl, InputLabel, MenuItem, Stack, TextField, Typography} from "@mui/material";
import {useEffect, useState} from "react";
// import {useDebounce} from "./useDebounce";
import { useNavigate } from "react-router-dom";
import Select from "./Select";


function AddShop({ user }) {
    const [country, setCountry] = useState("")
    const [city, setCity] = useState("")
    const [street, setStreet] = useState("")
    const [number, setNumber] = useState("")
    const [addressId, setAddressId] = useState(0)
    let navigate = useNavigate();

    const add = () => {
        if (country !== "" && city !== "" && street !== "" && number !== "") {
            let address = {
                kraj: country,
                miasto: city,
                ulica: street,
                numer: number
            }
            fetch("/api/adres/", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(address)
            }).then((res) => res.json())
                .then((data) => {
                    console.log(data)
                    if (data > 0) {
                        setAddressId(data)
                    }
                })
                .catch((error) => console.log(error))
        } else {
            alert("Pola nie mogą być puste")
        }
    }

    useEffect(() => {
        if (addressId !== 0) {
            let shop = {
                adresId: addressId,
                managerId: user.id
            }
            fetch("/api/sklep/", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(shop)
            }).then((res) => res.json())
                .then((data) => {
                    console.log(data)
                    if (data > 0) {
                        navigate("../manager");
                    }
                    // window.location.reload()
                })
                .catch((error) => console.log(error))
        }
    }, [addressId])


    return (
        <Stack direction="column" alignItems="center" spacing={2} mt={4} >
            <Typography>Dodaj sklep</Typography>
            <TextField label="Kraj" value={country} onChange={(e) => setCountry(e.target.value)} />
            <TextField label="Miasto" value={city} onChange={(e) => setCity(e.target.value)} />
            <TextField label="Ulica" value={street} onChange={(e) => setStreet(e.target.value)} />
            <TextField label="Numer domu" value={number} onChange={(e) => setNumber(e.target.value)} />
            <Button variant="contained" onClick={add}>Dodaj sklep</Button>
        </Stack>
    )
}

export default AddShop;