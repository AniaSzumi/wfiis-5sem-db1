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
    const [summary, setSummary] = useState({})
    const [cheapest, setCheapest] = useState([])

    const getNames = (items) => {
        return items.map((item) => item.nazwa + " (" + item.cena + " zł)")
    }

    useEffect(() => {
        fetch("/api/sklep")
            .then((res) => res.json())
            .then((data) => setShops(data))
            .catch((error) => console.log(error))
        fetch("/api/zamowienie/"+user.id)
            .then((res) => res.json())
            .then((data) => setOrders(data))
            .catch((error) => console.log(error))
        fetch("/api/zamowienie/summary/"+user.id)
            .then((res) => res.json())
            .then((data) => setSummary(data))
            .catch((error) => console.log(error))
        fetch("/api/produkt/cheapest/")
            .then((res) => res.json())
            .then((data) => setCheapest(data))
            .catch((error) => console.log(error))
    }, [])

    useEffect(() => {
        let id = currentShop.split(".")[0]
        if (id !== "") {
            fetch("/api/kategoria/sklep/" + id)
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
            fetch("/api/produkt/kategoria?" + new URLSearchParams({sklepId: id, kategoria: currentCategory}))
                .then((res) => res.json())
                // .then((data) => console.log(data))
                .then((data) => setProducts(data))
                .catch((error) => console.log(error))
        }
    }, [currentCategory])

    function submit() {
        if (currentShop !== "" && currentCategory !== "" && currentProduct !== "") {
            let product = products.find((product) => product.nazwa === currentProduct.split(" ")[0])
            let zamowienie = {
                klientId: user.id,
                sklepId: currentShop.split(".")[0],
                produktId: product.produktId,
                ilosc: count,
                suma: product.cena*count
            }
            console.log(JSON.stringify(zamowienie))
            fetch("/api/zamowienie", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(zamowienie)
            }).then((res) => res.text())
                .then((data) => {
                    data = parseInt(data)
                    if (data > 0) {
                        alert("Dodano do zamówienia")
                    } else if (data === -1) {
                        alert("Niepoprawne wartości")
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
                {Object.keys(summary).length > 0 &&
                <Typography variant="body1" mt={2}>
                    Ilość złożonych zamówień: {summary.zamowienia}<br />
                    Ilość zamówionych produktów: {summary.produkty}<br />
                    Łączna cena: {summary.cena} zł
                </Typography>}
                <Typography variant="h5" mt={2}>Lista zamówień</Typography>
                <List style={{marginTop: 0}}>
                    {orders.map((order) => (
                        <ListItem disableGutters style={{justifyContent: "flex-start"}} key={order.id} ml={0}>
                            {/*<ListItemIcon><ArrowRightIcon /></ListItemIcon>*/}
                            <ListItemText style={{margin: 0}} primary={order.nazwa+" - "+order.ilosc+" x "+order.cena+" zł ("+order.sklepId+". "+order.kraj+", "+order.miasto+" "+order.ulica+" "+order.numer+")"}/>
                        </ListItem>
                    ) )}
                </List>
                {cheapest.length > 0 &&
                <>
                <Typography variant="h5">Ilość najtańszych produktów (&lt; 15 zł)</Typography>
                <List style={{marginTop: 0}}>
                    {cheapest.map((category) => (
                        <ListItem disableGutters style={{justifyContent: "flex-start"}} key={category.id} ml={0}>
                            <ListItemText primary={category.nazwa+": "+category.produkty}/>
                        </ListItem>
                    ) )}
                </List>
                </>
                }
            </Stack>
        </Stack>
        </>
    )
}

export default Client