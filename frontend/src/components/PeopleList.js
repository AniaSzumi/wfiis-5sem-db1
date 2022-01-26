import {Divider, List, ListItem, ListItemText, Stack, Typography} from "@mui/material";

function PeopleList({ title, people, children }) {

    return (
        // <Stack direction="column">
        <div>
        <Typography variant="h5">{title}</Typography>
        <List>
            {people.map((person) => (
                <ListItem disableGutters key={person.imie+person.nazwisko}><ListItemText primary={person.imie+" "+person.nazwisko} /></ListItem>
            ))}
        </List>
            {children}
        </div>
        // </Stack>
    )
}

export default PeopleList