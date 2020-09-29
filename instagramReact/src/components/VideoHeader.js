import React from 'react'
import './VideoHeader.css'
import KeyboardArrowDownIcon from '@material-ui/icons/KeyboardArrowDown';
import KeyboardArrowUpIcon from '@material-ui/icons/KeyboardArrowUp';

function VideoHeader() {
    return (
        <div className="videoHeader">
            <KeyboardArrowUpIcon className="up" />
            <KeyboardArrowDownIcon className="down" />
        </div>
    )
}

export default VideoHeader
