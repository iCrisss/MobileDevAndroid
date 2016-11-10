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

export default class InputForm extends Component{ 
	buttonGoToListingOnPress() {
		this.props.navigator.push({
			name: 'listContainer'
		})
	};
	
	buttonSubmitOnPress() {
		
	}

	render() {
		
		return(
			<View style={styles.container}>
        <Text style={styles.textName}>
          Name:
        </Text>
        <TextInput style={styles.inputName}
        />
        <Text style={styles.textPassword}>
          Password:
        </Text>
		<TextInput style={styles.inputName}
		/>
		<Text style={styles.textEmail}>
          Email:
        </Text>
		<TextInput style={styles.inputName}
		/>
		<Text style={styles.textPhoneNumber}>
          Phone number:
        </Text>
		<TextInput style={styles.inputName}
		/>
		<Text style={styles.textPostalAddress}>
          Postal address:
        </Text>
		<TextInput style={styles.inputName}
		/>
		<Button style={styles.buttonSubmit}
			onPress={this.buttonSubmitOnPress.bind(this)}
			title="Submit"
		/>
		<Button style={styles.buttonGoToListing}
			onPress={this.buttonGoToListingOnPress.bind(this)}
			title="Go to listing"
		/>
      </View>
		);
	}
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
	flexDirection: 'column',
    justifyContent: 'flex-start',
    alignItems: 'flex-start',
    backgroundColor: '#F5FCFF',
  },
  textName: {
    fontSize: 10,
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
  textPassword: {
    fontSize: 10,
    textAlign: 'left',
    margin: 10,
  },
  inputPassword: {
	  height: 50,
	  padding: 4,
	  marginRight: 5,
	  flex: 4,
	  fontSize: 10,
	  borderWidth: 1,
	  borderColor: '#48BBEC',
	  borderRadius: 8,
	  color: '#48BBEC',
	  width: 150
  },
  textEmail: {
	  fontSize: 10,
    textAlign: 'left',
    margin: 10,
  },
  inputEmail: {
	  height: 20,
	  padding: 4,
	  marginRight: 5,
	  flex: 4,
	  fontSize: 10,
	  borderWidth: 1,
	  borderColor: '#48BBEC',
	  borderRadius: 8,
	  color: '#48BBEC',
	  width: 150
  },
  textPhoneNumber: {
	  fontSize: 10,
    textAlign: 'left',
    margin: 10,
  },
  inputPhoneNumber: {
	  height: 20,
	  padding: 4,
	  marginRight: 5,
	  flex: 4,
	  fontSize: 10,
	  borderWidth: 1,
	  borderColor: '#48BBEC',
	  borderRadius: 8,
	  color: '#48BBEC',
	  width: 150
  },
  textPostalAddress: {
	  fontSize: 10,
    textAlign: 'left',
    margin: 10,
  },
  inputPostalAddress: {
	  height: 20,
	  padding: 4,
	  marginRight: 5,
	  flex: 4,
	  fontSize: 10,
	  borderWidth: 1,
	  borderColor: '#48BBEC',
	  borderRadius: 8,
	  color: '#48BBEC',
	  width: 150
  },
  buttonSubmit: {
	  height: 30,
	  width: 100,
	  margin: 10,
	  alignSelf: 'flex-start'
  },
  buttonGoToListing: {
	  height: 30,
	  width: 100,
	  alignSelf: 'flex-end'
  }
});