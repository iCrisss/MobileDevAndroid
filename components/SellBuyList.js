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
			db: ['First Item', 'Second Item' ],
			items: 0,
			dataSource: ds.cloneWithRows(['First Item', 'Second Item' ]),
		};
	}
	
	onPressItem(rowData, rowId) {
		this.props.navigator.push({
			name: 'editListItem',
			passProps: {
				item: rowData,
				rowId: rowId
			}
		})
	}
	
	componentWillMount() {
		const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});
		if('rowId' in this.props){
			var newArray = this.state.db.slice();
			newArray[this.props.rowId] = this.props.item;
			debugger;
			this.setState({
				dataSource: ds.cloneWithRows(newArray),
				db: newArray,
			});
		}
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