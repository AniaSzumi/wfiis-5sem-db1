import {TextField} from "@mui/material";
import {useState} from "react";

function NumField({ label, value, onChange }) {
    const [numError, setNumError] = useState(false)
    const [text, setText] = useState('')

    function validateNumber(e) {
        if(!/\D/.test(e.target.value) || e.target.value.match(/[.,]/)) {
            setNumError(false)
            setText('')
        } else {
            setNumError(true)
            setText("Proszę wprowadzić cyfrę")
        }
        onChange(e.target.value)
    }
    return (
        <TextField helperText={text} value={value} onChange={(e) => validateNumber(e)} error={numError} label={label} fullWidth />
    )
}

export default NumField