'use strict';
 
import React, { Component } from 'react';
import {
  AppRegistry,
  StyleSheet,
  Text,
  TextInput,
  Button,
  View,
  ListView,
  Navigator,
  TouchableHighlight
} from 'react-native';

import EditListItem from './EditListItem';
export default class SellBuyListItem extends Component{

	render() {
		return(
			<TouchableHighlight onPress={this.props.onPress.bind(this)}>
				<Text style={styles.textName}>
				{this.props.item}
				</Text>
			</TouchableHighlight>
		);
	}
}

const styles = StyleSheet.create({
  textName: {
    fontSize: 20,
    textAlign: 'left',
    margin: 10,
  },
});