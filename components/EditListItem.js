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
  TouchableHighlight,
  Navigator
} from 'react-native';

export default class EditListItem extends Component{
	
	onPress() {
		this.props.navigator.pop();
	}
	
	render() {
		return(
			<View>
			<Text style={styles.textName}>
				{this.props.item}
			</Text>
			<TouchableHighlight onPress={this.onPress.bind(this)}>
				<Text style={styles.textName}>
					Back to listing
				</Text>
			</TouchableHighlight>
			</View>
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