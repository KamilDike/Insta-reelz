import React, { useRef, useState } from 'react'
import './VideoCard.css'
import VideoFooter from './VideoFooter';
import VideoHeader from './VideoHeader';

function VideoCard({avatarSrc, channel, shares, title, url, watches}) {
    const [isVideoPlaying, setIsVideoPlaying] = useState(false)
    const videoRef = useRef(null);

    const onVideoPress = () => {
        setIsVideoPlaying(!isVideoPlaying)

        if (isVideoPlaying) {
            videoRef.current.pause();
            setIsVideoPlaying(false)
        } else {
            videoRef.current.play();
            setIsVideoPlaying(true)
        }
    }

    return (
        <div className="videoCard">
            <VideoHeader/>
            <video
                ref={videoRef}
                onClick={onVideoPress}
                className="videoCard__player"
                src={url}
                alt="IG reel video"
                loop
            />
            <VideoFooter channel={channel} avatarSrc={avatarSrc} title={title}/>
        </div>
    )
}

export default VideoCard
