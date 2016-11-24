'use strict';
 
import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  TextInput,
  Button,
  View,
  Navigator
} from 'react-native';

import SellBuyListContainer from './components/SellBuyListContainer';
import EditListItem from './components/EditListItem';
import InputForm from './components/InputForm';

export default class try1 extends Component {
	
	renderScene(route, navigator) {
		if(route.name == 'inputForm'){
			return <InputForm navigator={navigator} />
		}
		if(route.name == 'listContainer'){
			return <SellBuyListContainer navigator={navigator} route = {route}/>
		}
		if(route.name == 'editListItem'){
			return <EditListItem navigator={navigator} {...route.passProps} />
		}
	};
  render() {
    return (
      <Navigator 
		initialRoute={{
			name : 'inputForm',
		}}
		renderScene = {this.renderScene.bind(this)}
		/>
		);
  };
}

const styles = StyleSheet.create({
  
});

AppRegistry.registerComponent('try1', () => try1);
