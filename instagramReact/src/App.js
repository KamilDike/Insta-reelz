import React, { useState, useEffect } from 'react';
import './App.css';
import VideoCard from './components/VideoCard';
import db from './firebase'
import { BrowserRouter as Router, Switch, Route, Redirect, withRouter, useParams } from 'react-router-dom'
import AddClip from './components/AddClip';
import {shuffleArray} from './components/useCategorySearch'
import { useStateValue } from './StateProvider'
import { actionTypes } from './reducer';
import CategoryBar from './components/CategoryBar';

function App() {
  const [reels, setReels] = useState([])

  useEffect(() => {
    console.log(reels)
  }, [reels])

  useEffect(() => {
    db.collection('categories').doc('basketball').collection('reels').onSnapshot(snapshot => {
      setReels(shuffleArray(snapshot.docs.map(doc => doc.data())));
    })
  }, [])

  function queryDB(name) {
    if(name) {
      db.collection('categories').doc(name).collection('reels').onSnapshot(snapshot => {
        setReels(shuffleArray(snapshot.docs.map(doc => doc.data())));
      })
    }
  }

  return (
    <div className="app">
    <Router>
    <Route exact path="/">
      <Redirect to="/home" />
    </Route>
      <Switch>
        <Route path="/add">
          <AddClip/>
        </Route>
        <Route path="/home">
        <div className="app__videos">
            {reels.map(({ avatarSrc, channel, message, url }) => (
              <VideoCard
                channel={channel}
                avatarSrc={avatarSrc}
                url={url}
                title={message}
              />
            ))}
        </div>
        </Route>
      </Switch>
    </Router>
    </div>
  );
}

export default App;
