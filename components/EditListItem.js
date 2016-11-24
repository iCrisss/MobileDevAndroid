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
	constructor(props) {
		super(props);
		
		this.state = {
			itemNameText: ''
		}
	}
	
	onPress() {
		this.props.navigator.push({
			name: 'listContainer',
			passProps: {
				item: this.state.itemNameText,
				rowId: this.props.rowId
			}
		})
	}
	
	render() {
		return(
			<View>
			<Text style={styles.textName}>
				{this.props.item}
			</Text>
			<TextInput 
				autoFocus={true}
				value={this.state.itemNameText}
				style={styles.inputName}
				onChangeText={itemNameText => this.setState({itemNameText})}
			/>
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
  inputName: {
	  height: 50,
	  fontSize: 15,
	  borderWidth: 1,
	  borderColor: '#48BBEC',
	  borderRadius: 8,
	  color: '#48BBEC',
	  width: 250
  },
});