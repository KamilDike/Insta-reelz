import React, {useState, useRef, useEffect} from 'react'
import { useHistory } from 'react-router'
import './CategoryBar.css'

function CategoryBar({text,background}) {

    const categoryBar = useRef(null)

    useEffect(() => {
        categoryBar.current.style.background = `rgb(${Math.random()*256},${Math.random()*256},100)`
    }, [])

    return (
        <div className="categoryBar" ref={categoryBar}>
            <p className="categoryBar__text">{text}</p>
        </div>
    )
}

export default CategoryBar
