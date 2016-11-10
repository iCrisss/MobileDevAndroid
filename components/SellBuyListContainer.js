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

import SellBuyList from './SellBuyList';

export default class SellBuyListContainer extends Component{
	
	render() {
		return (
			<SellBuyList navigator={this.props.navigator}/>
		)
	};
}