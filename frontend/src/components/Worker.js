import {Button, List, ListItem, ListItemText, Stack, TextField, Typography} from "@mui/material";
import AddCircleOutlineIcon from '@mui/icons-material/AddCircleOutline';
import {useEffect, useState} from "react";
import LogoutButton from "./LogoutButton";
import PeopleList from "./PeopleList";
import {useNavigate} from "react-router-dom";
import Select from "./Select";
import NumField from "./NumField";

function Worker({ user }) {
    const [shops, setShops] = useState([])
    const [inventoryShop, setInventoryShop] = useState("")
    const [allProducts, setAllProducts] = useState([])
    const [inventoryProduct, setInventoryProduct] = useState("")
    const [inventoryAmount, setInventoryAmount] = useState(0)
    const [currentShop, setCurrentShop] = useState('')
    const [categories, setCategories] = useState([])
    const [shopProducts, setShopProducts] = useState([])
    const [shopAmount, setShopAmount] = useState([])
    const [productName, setProductName] = useState("")
    const [productCategory, setProductCategory] = useState("")
    const [productPrice, setProductPrice] = useState(0)
    const [categoryName, setCategoryName] = useState("")

    const navigate = useNavigate();

    useEffect(() => {
        fetch("/api/sklep")
            .then((res) => res.json())
            .then((data) => setShops(data))
            .catch((error) => console.log(error))
        fetch("/api/kategoria")
            .then((res) => res.json())
            .then((data) => setCategories(data))
            .catch((error) => console.log(error))
        fetch("/api/produkt/")
            .then((res) => res.json())
            .then((data) => setAllProducts(data))
            .catch((error) => console.log(error))
        fetch("/api/sklep/produkty")
            .then((res) => res.json())
            .then((data) => setShopAmount(data))
            .catch((error) => console.log(error))
    }, [])

    useEffect(() => {
        let id = currentShop.split(".")[0]
        if (id !== "") {
            fetch("/api/produkt/sklep/" +id)
                .then((res) => res.json())
                .then((data) => setShopProducts(data))
                .catch((error) => console.log(error))
        }
    }, [currentShop])

    const addProduct = () => {
        let catId = categories.find(c => c.nazwa === productCategory).id
        let product = {
            nazwa: productName,
            kategoriaId: catId,
            cena: productPrice
        }
        if (productName !== "" && productCategory !== "" && productPrice !== 0) {
            fetch("/api/produkt", {
                method: 'POST',
                mode: "cors",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(product)
            }).then((res) => res.text())
                .then((data) => {
                    data = parseInt(data)
                    if (data > 0) {
                        console.log("dodano")
                    } else if (data === -1) {
                        alert("Niepoprawne dane produktu")
                    } else {
                        console.log("nie dodano")
                    }
                    window.location.reload()
                }).catch((err) => console.log(err))
        } else {
            alert("Pola nie mogą być puste")
        }
    }

    const addCategory = () => {
        if (categoryName !== "") {
            fetch("/api/kategoria/", {
                method: 'POST',
                mode: "cors",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify({nazwa: categoryName})
            }).then((res) => res.text())
                .then((data) => {
                    console.log(data)
                    if (data > 0) {
                        console.log("dodano")
                    } else {
                        console.log("nie dodano")
                    }
                    window.location.reload()
                }).catch((err) => console.log(err))
        } else {
            alert("Pola nie mogą być puste")
        }
    }

    const addInventory = () => {
        if (inventoryProduct !== "" && inventoryShop !== "" && inventoryAmount !== 0) {
            let inventory = {
                produktId: allProducts.find(p => p.nazwa === inventoryProduct).id,
                sklepId: inventoryShop.split(".")[0],
                ilosc: inventoryAmount
            }
            fetch("/api/produkt/sklep", {
                method: 'POST',
                mode: "cors",
                headers: {'Content-Type': 'application/json'},
                body: JSON.stringify(inventory)
            }).then((res) => res.text())
                .then((data) => {
                    if (data > 0) {
                        console.log("dodano")
                    }
                    window.location.reload()
                })
                .catch((err) => console.log(err))
        } else {
            alert("Pola nie mogą być puste")
        }
    }

    return (<>
        <center>
            <Typography variant="subtitle1" mt={2}>Pracownik: {user.imie+" "+user.nazwisko}</Typography>
            <LogoutButton style={{marginTop: 0}}/>
        </center>
        <Stack direction="row" justifyContent="space-around" mt={2}>
            <Stack direction="column" alignItems="flex-start" spacing={2} mt={2}>
                <Typography variant="h5">Dodaj nowy produkt</Typography>
                <TextField label="Nazwa" value={productName} onChange={(e) => setProductName(e.target.value)} fullWidth variant="outlined"/>
                <Select label="Kategoria" items={categories.map(c => c.nazwa)} value={productCategory} onChange={setProductCategory} />
                <NumField label="Cena" value={productPrice} onChange={setProductPrice} />
                <Button variant="contained" onClick={addProduct}>Dodaj</Button>
                <Typography variant="h5" style={{marginTop: "30px"}}>Dodaj nową kategorię</Typography>
                <TextField label="Nazwa" value={categoryName} onChange={(e) => setCategoryName(e.target.value)} fullWidth variant="outlined"/>
                <Button variant="contained" onClick={addCategory}>Dodaj</Button>
            </Stack>
            <Stack direction="column" alignItems="flex-start" spacing={2} mt={2}>
                <Typography variant="h5">Dodaj produkt do sklepu</Typography>
                <Select label="Sklep" items={shops} value={inventoryShop} onChange={setInventoryShop} />
                <Select label="Produkt" items={allProducts.map(p => p.nazwa)} value={inventoryProduct} onChange={setInventoryProduct} />
                <NumField label="Ilość" value={inventoryAmount} onChange={setInventoryAmount} />
                <Button variant="contained" onClick={addInventory}>Dodaj do sklepu</Button>
            </Stack>
            <Stack direction="column" alignItems="flex-start" spacing={2} mt={2}>
                <Typography variant="h5">Ilość produktów w sklepach</Typography>
                <List>
                    {shopAmount.map((amount) => <ListItem key={amount.id}><ListItemText primary={amount.id+". "+amount.kraj+", "+amount.miasto+" "+amount.ulica+" "+amount.numer+" - "+amount.produkty+" szt"}/></ListItem>)}
                </List>
                <Typography variant="h5">Przeglądaj produkty w sklepie</Typography>
                <Select label="Sklep" items={shops} value={currentShop} onChange={setCurrentShop} />
                {shopProducts.length > 0 &&
                <List>
                    {shopProducts.map((product) => <ListItem key={product.nazwa}><ListItemText primary={product.nazwa+" ("+product.ilosc+")"}/></ListItem>)}
                </List>
                }
            </Stack>
        </Stack>
    </>)
}

export default Worker