import {FormControl, InputLabel, MenuItem, Select as MuiSelect} from "@mui/material";

function Select({ label, items, value, onChange, style }) {
    return (
        // <form>
        //     <label>{label}:{' '}
        //     <select value={value} onChange={(e) => onChange(e.target.value)}>
        //         <option value="" key="default" hidden>Wybierz</option>
        //         {items.map((item) => <option value={item} key={item}>{item}</option>)}
        //     </select>
        //     </label>
        // </form>
        <FormControl fullWidth style={style}>
            <InputLabel id={label + "-label"}>{label}</InputLabel>
            <MuiSelect
                labelId={label + "-label"}
                id={label}
                value={value}
                label={label}
                onChange={(e) => onChange(e.target.value)}
            >
                {items.map((item) => <MenuItem value={item} key={item}>{item}</MenuItem>)}
            </MuiSelect>
        </FormControl>
    )
}

export default Select