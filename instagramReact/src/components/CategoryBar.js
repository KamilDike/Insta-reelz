import React, {useState} from 'react'
import { useHistory } from 'react-router'
import './CategoryBar.css'

function CategoryBar(props) {
    const [input, setInput] = useState('')
    const history = useHistory();

    const options = [
        { value: 'basketball', label: 'Basketball' },
        { value: 'vines', label: 'Vines' },
        { value: 'vanilla', label: 'Vanilla' }
      ]

    const setCategory = (e) => {
        e.preventDefault()
        console.log(input)

        setInput('')


        history.push(`/${input}`)
    }

    return (
        <div className="categoryBar">
            <form onSubmit={setCategory} onChange={e => setInput(e.target.value)} id='form'>
                <select id="cars" name="cars" form='form'>
                    <option value="" selected disabled hidden>Choose Category</option>
                    <option value="volvo">Volvo</option>
                    <option value="saab">Saab</option>
                    <option value="fiat">Fiat</option>
                    <option value="audi">Audi</option>
                </select>
                <div>
                    <button>Submit</button>
                </div>
            </form>
        </div>
    )
}

export default CategoryBar
