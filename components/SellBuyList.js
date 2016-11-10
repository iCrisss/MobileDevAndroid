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
  Navigator
} from 'react-native';

import SellBuyListItem from './SellBuyListItem';

export default class SellBuyList extends Component {
	constructor(props) {
		super(props);
		
		const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
		
		this.state = {
			items: 0,
			dataSource: ds.cloneWithRows(['row 1', 'row 2']),
		};
	}
	
	onPressItem(rowData, rowId) {
		this.props.navigator.push({
			name: 'editListItem',
			item: {rowData}
		})
	}
	
	render() {
		
		return (
			<ListView 
				dataSource = {this.state.dataSource}
				renderRow = {(rowData, sectionId, rowId) => {
					return (
						<SellBuyListItem
							item = {rowData}
							navigator = {this.props.navigator}
							onPress = {() => this.onPressItem(rowData, rowId)}
						/>
					);
				}}
			/>
		);
	}
}