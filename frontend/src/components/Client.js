import {useEffect, useState} from "react";
import Select from "./Select";
import {Button, Grid, List, ListItem, ListItemIcon, ListItemText, Stack, TextField, Typography} from "@mui/material";
import NumField from "./NumField";
import LogoutButton from "./LogoutButton";
import CheckCircleOutlineIcon from '@mui/icons-material/CheckCircleOutline';
import ArrowRightIcon from '@mui/icons-material/ArrowRight';

function Client({ user }) {
    const [shops, setShops] = useState([])
    const [currentShop, setCurrentShop] = useState('')
    const [categories, setCategories] = useState([])
    const [currentCategory, setCurrentCategory] = useState("")
    const [products, setProducts] = useState([])
    const [currentProduct, setCurrentProduct] = useState("")
    const [count, setCount] = useState(0)
    const [orders, setOrders] = useState([])

    const getNames = (items) => {
        return items.map((item) => item.nazwa + " (" + item.cena + " zł)")
    }

    useEffect(() => {
        fetch("/sklep")
            .then((res) => res.json())
            .then((data) => setShops(data))
            .catch((error) => console.log(error))
        fetch("/zamowienie/"+user.id)
            .then((res) => res.json())
            // .then(data => console.log(data))
            .then((data) => setOrders([data]))
            .catch((error) => console.log(error))
    }, [])

    useEffect(() => {
        let id = currentShop.split(".")[0]
        if (id !== "") {
            fetch("/kategoria/sklep/" + id)
                .then((res) => res.json())
                // .then((data) => console.log(data))
                .then((data) => setCategories(data))
                .catch((error) => console.log(error))
        }
        setCurrentCategory('')
        setProducts([])
        setCurrentProduct('')
    }, [currentShop])

    useEffect(() => {
        let id = currentShop.split(".")[0]
        if (currentCategory !== "") {
            fetch("/produkt/kategoria?" + new URLSearchParams({sklepId: id, kategoria: currentCategory}))
                .then((res) => res.json())
                // .then((data) => console.log(data))
                .then((data) => setProducts(data))
                .catch((error) => console.log(error))
        }
    }, [currentCategory])

    function submit() {
        if (currentShop !== "" && currentCategory !== "" && currentProduct !== "") {
            let zamowienie = {
                klientId: user.id,
                sklepId: currentShop.split(".")[0],
                produktId: products.find((product) => product.nazwa === currentProduct.split(" ")[0]).produktId,
                ilosc: count
            }
            console.log(JSON.stringify(zamowienie))
            fetch("/zamowienie", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(zamowienie)
            }).then((res) => res.json())
                .then((data) => {
                    if (data != null) {
                        alert("Dodano do zamówienia")
                    } else {
                        alert("Nie udało się dodać zamówienia")
                    }
                    window.location.reload()
                })
        }
        else {
            alert("Proszę uzupełnić wszystkie pola")
        }
    }

    return (
        <>
        <center><Typography variant="subtitle1" mt={4}>Klient: {user.imie+" "+user.nazwisko}</Typography>
        <LogoutButton/> </center>
        <Stack direction="row" justifyContent="space-evenly" >
            <Stack direction="column" alignItems="flex-start" spacing={2} mt={4}>
                <Typography variant="h5">Zamów produkt</Typography>
                <Select label="Sklep" items={shops} value={currentShop} onChange={setCurrentShop} />
                <Select label="Kategoria" items={categories} value={currentCategory} onChange={setCurrentCategory} />
                <Select label="Produkt" items={getNames(products)} value={currentProduct} onChange={setCurrentProduct} />
                <NumField label="Ilość" value={count} onChange={setCount} />
                <Button variant="contained" onClick={submit}>Zamów</Button>
            </Stack>
            <Stack direction="column" alignItems="flex-start" mt={4}>
                <Typography variant="h5">Podsumowanie</Typography>
                <List>
                    {orders.map((order) => (
                        <ListItem disableGutters style={{justifyContent: "flex-start"}} key={order.id} ml={0}>
                            {/*<ListItemIcon><ArrowRightIcon /></ListItemIcon>*/}
                            <ListItemText primary={order.nazwa+" - "+order.ilosc+" x "+order.cena+" zł"}/>
                        </ListItem>
                    ) )}
                </List>
            </Stack>
        </Stack>
        </>
    )
}

export default Client