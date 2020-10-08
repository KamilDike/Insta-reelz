import React, { useState, useEffect } from 'react';
import './App.css';
import VideoCard from './components/VideoCard';
import db from './firebase'
import { BrowserRouter as Router, Switch, Route, Redirect, withRouter, useParams, Link, useHistory, useLocation } from 'react-router-dom'
import AddClip from './components/AddClip';
import {shuffleArray} from './components/useCategorySearch'
import { useStateValue } from './StateProvider'
import { actionTypes } from './reducer';
import CategoryBar from './components/CategoryBar';

function App() {
  const [reels, setReels] = useState([])
  const [categories, setCategories] = useState([])

  useEffect(() => {
    // db.collection('categories').doc('basketball').collection('reels').onSnapshot(snapshot => {
    //   setReels(shuffleArray(snapshot.docs.map(doc => doc.data())));
    // })

    db.collection("info").onSnapshot(snapshot => {
      setCategories(snapshot.docs.map(doc => doc.id))
    });
  }, [])

  async function queryDB(category) {
    db.collection('categories').doc(category).collection('reels').onSnapshot(snapshot => 
        snapshot.docs.map(doc => console.log(doc.data()))
      )
  }

  return (
    <Router>
      <Switch>
        <Route exact={true} path="/">
          {categories.map(category => (
            <Link to={category}>
              <CategoryBar text={category}/>
            </Link>
          ))}
        </Route>
        <Route path="/add">
          <AddClip/>
        </Route>
        <Route path="/home">
        <div className="app">
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
        </div>
        </Route>
      </Switch>
    </Router>
  );
}

export default App;
