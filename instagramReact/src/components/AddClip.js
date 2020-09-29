import React, {useState} from 'react'
import './AddClip.css'
import db from '../firebase'

function AddClip() {

    const [text, setText] = useState('')

    const onSubmit = (e) => {
        e.preventDefault();
        const obj = JSON.parse(text);

        if (obj) {
            db.collection('reels').add({
                avatarSrc: obj.avatarSrc,
                title: obj.title,
                url: obj.url,
                channel: obj.channel
            })
        }
        setText('')
    }

    return (
        <div>
            <form onSubmit={onSubmit}>
                <input className="addClip" value={text} onChange={(e) => {setText(e.target.value)}}/>
            </form>
        </div>
    )
}

export default AddClip
