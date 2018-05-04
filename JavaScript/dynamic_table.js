


jQuery(document).ready(function($){

	/* 
		dynamicTable tool: manage the table element. update it dynamically

		combine anonymous function and return object to separate private members from class
	*/
	var dynamicTable = (function(){
		var _tableId,_table,_fields,_headers,_defaultText;
		// build one html row
		// item is a row object, if it is null, use names as the text value
		function _buildRow(names, item){
			var row='<tr>';
			if(names && names.length > 0){
				$.each(names, function(index, name){
					var col = item?item[name+""]:name;
					var tdWidth = 100/_fields.length;
					row +='<td style="width: '+ tdWidth + '%;">'+col+'</td>';
				});
			}
			row +='</tr>';
			return row;
		}

		//set the hearder of the table
		function _setHeaders(){
			// if no headers, we use _fields as headers
			_headers = (_headers == null || _headers.length < 1)?_fields : _headers;
			var h = _buildRow(_headers);
			if(_table.children("thead").length < 1){
				// insert the thead element as the first node in table element
				_table.prepend('<thead></thead>');
			}
			_table.children('thead').html(h);
		}

		// if there is no item to show, show the default text
		function _setNoItemsInfo(){
			if(_table.length < 1) return; // haven't be configured.
			var colspan = _headers != null && _headers.length > 0 ? 'colspan="'+_headers.length+'"':'';
			var content = '<tr class="no-items"><td ' + colspan + ' style="text-align:center">' + 
            _defaultText + '</td></tr>';
            if(_table.children('tbody').length > 0)
            	_table.children('tbody').html(content);
            else
            	_table.append('<tbody>'+content+'</tbody>');
		}
		// remove default text
		function _removeNoItemsInfo() {
			var c = _table.children('tbody').children('tr');
			if(c.length == 1 && c.hasClass('no-items'))
				_table.children('tbody').empty();
		}

		return {
			config: function(tableId, fields, headers, defaultText){
				_tableId = tableId;
				_table = $('#'+tableId);
				_fields = fields || null;
	            _headers = headers || null;
	            _defaultText = defaultText || 'No items to list...';
	            _setNoItemsInfo();
	            return this;
			},
			// append: if append the rows in data to the end of the table or re set the table
			load: function(data, append){
				if(_table.length < 1) 
					return; 
				_removeNoItemsInfo();
				if(data && data.length > 0){
					var rows = '';
					$.each(data, function(index, item){
						rows += _buildRow(_fields, item);
					});
					var method = append?'append':'html';
					_table.children('tbody')[method](rows);
					var rows = _table.find('tr');
					// add hover effects on table row
					rows.hover(
						function(){
							$(this).attr('class', 'z-depth-3');
							$('#message').html('hovered row: '+$(this).text());
						},
						function(){
							$(this).attr('class', '');
						}
					);
					// change the color when click table row
					rows.click(function(){
						remove_color(rows);
						$(this).css('background-color','pink');
						appt_type_selected_row = $(this);
						$('#message_2').html('selected row: '+appt_type_selected_row.text());
						// console.log('click:'+$('#message_2').text());
					});

				}
				else
					_setNoItemsInfo();
				return this;
			},
			setHeaders: function(){
				if(_table.length < 1) 
					return; 
				_setHeaders();
			},
			clear: function(){
				_setNoItemsInfo();
				return this;
			}
		}
	})();
});

