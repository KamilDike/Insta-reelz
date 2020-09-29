import React from 'react'
import './VideoFooter.css'
import { Avatar } from '@material-ui/core'
import Ticker from "react-ticker"

function VideoFooter({ avatarSrc, channel, title }) {
    return (
        <div className='videoFooter'>
            <div className="videoFooter__text">
                <Avatar src={avatarSrc} />
                <h3>{channel}</h3>
            </div>
            <div className="videoFooter__ticker">
                <Ticker mode="smooth" speed="8">
                    {({ index }) => (
                        <>
                            <h1>{title}</h1>
                        </>
                    )}
                </Ticker>
            </div>
        </div>
    )
}

export default VideoFooter
