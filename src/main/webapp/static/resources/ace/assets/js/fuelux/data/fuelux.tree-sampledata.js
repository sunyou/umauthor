var DataSourceTree = function(options) {
	this._data 	= options.data;
	this._delay = options.delay;
}

DataSourceTree.prototype.data = function(options, callback) {
	var self = this;
	var $data = null;
	
	if(!("name" in options) && !("type" in options)){
		$data = this._data;//the root tree
		callback({ data: $data });
		return;
	}
	else if("type" in options && options.type == "folder") {
		if("additionalParameters" in options && "children" in options.additionalParameters)
			$data = options.additionalParameters.children;
		else $data = {}//no data
	}
	
	if($data != null)//this setTimeout is only for mimicking some random delay
		setTimeout(function(){callback({ data: $data });} , parseInt(Math.random() * 500) + 200);

	//we have used static data here
	//but you can retrieve your data dynamically from a server using ajax call
	//checkout examples/treeview.html and examples/treeview.js for more info
};

var tree_data = {
	'for-sale' : {name: 'For Sale', type: 'folder'}	,
	'vehicles' : {name: 'Vehicles', type: 'folder'}	,
	'rentals' : {name: 'Rentals', type: 'folder'}	,
	'real-estate' : {name: 'Real Estate', type: 'folder'}	,
	'pets' : {name: 'Pets', type: 'folder'}	,
	'tickets' : {name: 'Tickets', type: 'item'}	,
	'services' : {name: 'Services', type: 'item'}	,
	'personals' : {name: 'Personals', type: 'item'}
}
tree_data['for-sale']['additionalParameters'] = {
	'children' : {
		'appliances' : {name: 'Appliances', type: 'item'},
		'arts-crafts' : {name: 'Arts & Crafts', type: 'item'},
		'clothing' : {name: 'Clothing', type: 'item'},
		'computers' : {name: 'Computers', type: 'item'},
		'jewelry' : {name: 'Jewelry', type: 'item'},
		'office-business' : {name: 'Office & Business', type: 'item'},
		'sports-fitness' : {name: 'Sports & Fitness', type: 'item'}
	}
}
tree_data['vehicles']['additionalParameters'] = {
	'children' : {
		'cars' : {name: 'Cars', type: 'folder'},
		'motorcycles' : {name: 'Motorcycles', type: 'item'},
		'boats' : {name: 'Boats', type: 'item'}
	}
}
tree_data['vehicles']['additionalParameters']['children']['cars']['additionalParameters'] = {
	'children' : {
		'classics' : {name: 'Classics', type: 'item'},
		'convertibles' : {name: 'Convertibles', type: 'item'},
		'coupes' : {name: 'Coupes', type: 'item'},
		'hatchbacks' : {name: 'Hatchbacks', type: 'item'},
		'hybrids' : {name: 'Hybrids', type: 'item'},
		'suvs' : {name: 'SUVs', type: 'item'},
		'sedans' : {name: 'Sedans', type: 'item'},
		'trucks' : {name: 'Trucks', type: 'item'}
	}
}

tree_data['rentals']['additionalParameters'] = {
	'children' : {
		'apartments-rentals' : {name: 'Apartments', type: 'item'},
		'office-space-rentals' : {name: 'Office Space', type: 'item'},
		'vacation-rentals' : {name: 'Vacation Rentals', type: 'item'}
	}
}
tree_data['real-estate']['additionalParameters'] = {
	'children' : {
		'apartments' : {name: 'Apartments', type: 'item'},
		'villas' : {name: 'Villas', type: 'item'},
		'plots' : {name: 'Plots', type: 'item'}
	}
}
tree_data['pets']['additionalParameters'] = {
	'children' : {
		'cats' : {name: 'Cats', type: 'item'},
		'dogs' : {name: 'Dogs', type: 'item'},
		'horses' : {name: 'Horses', type: 'item'},
		'reptiles' : {name: 'Reptiles', type: 'item'}
	}
}

var treeDataSource = new DataSourceTree({data: tree_data});











var tree_data_2 = {
	'pictures' : {name: 'Pictures', type: 'folder', 'icon-class':'red', id : 1}	,
	'music' : {name: 'Music', type: 'folder', 'icon-class':'orange', id : 2}	,
	'video' : {name: 'Video', type: 'folder', 'icon-class':'blue', id : 3}	,
	'documents' : {name: 'Documents', type: 'folder', 'icon-class':'green', id : 4}	,
	'backup' : {name: 'Backup', type: 'folder', id : 5}	,
	'readme' : {name: '<i class="icon-file-text grey"></i> ReadMe.txt', type: 'item' , id : 6},
	'manual' : {name: '<i class="icon-book blue"></i> Manual.html', type: 'item', id : 7}
}
tree_data_2['music']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-music blue"></i> song1.ogg', type: 'item', id : 8},
		{name: '<i class="icon-music blue"></i> song2.ogg', type: 'item', id : 9},
		{name: '<i class="icon-music blue"></i> song3.ogg', type: 'item', id : 10},
		{name: '<i class="icon-music blue"></i> song4.ogg', type: 'item', id : 11},
		{name: '<i class="icon-music blue"></i> song5.ogg', type: 'item', id : 12}
	]
}
tree_data_2['video']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-film blue"></i> movie1.avi', type: 'item', id : 13},
		{name: '<i class="icon-film blue"></i> movie2.avi', type: 'item', id : 14},
		{name: '<i class="icon-film blue"></i> movie3.avi', type: 'item', id : 15},
		{name: '<i class="icon-film blue"></i> movie4.avi', type: 'item', id : 16},
		{name: '<i class="icon-film blue"></i> movie5.avi', type: 'item', id : 17}
	]
}
tree_data_2['pictures']['additionalParameters'] = {
	'children' : {
		'wallpapers' : {name: 'Wallpapers', type: 'folder', 'icon-class':'pink', id : 18},
		'camera' : {name: 'Camera', type: 'folder', 'icon-class':'pink', id : 19}
	}
}
tree_data_2['pictures']['additionalParameters']['children']['wallpapers']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-picture green"></i> wallpaper1.jpg', type: 'item', id : 20},
		{name: '<i class="icon-picture green"></i> wallpaper2.jpg', type: 'item', id : 21},
		{name: '<i class="icon-picture green"></i> wallpaper3.jpg', type: 'item', id : 22},
		{name: '<i class="icon-picture green"></i> wallpaper4.jpg', type: 'item', id : 23}
	]
}
tree_data_2['pictures']['additionalParameters']['children']['camera']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-picture green"></i> photo1.jpg', type: 'item', id : 24},
		{name: '<i class="icon-picture green"></i> photo2.jpg', type: 'item', id : 25},
		{name: '<i class="icon-picture green"></i> photo3.jpg', type: 'item', id : 26},
		{name: '<i class="icon-picture green"></i> photo4.jpg', type: 'item', id : 27},
		{name: '<i class="icon-picture green"></i> photo5.jpg', type: 'item', id : 28},
		{name: '<i class="icon-picture green"></i> photo6.jpg', type: 'item', id : 29}
	]
}


tree_data_2['documents']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-file-text red"></i> document1.pdf', type: 'item', id : 30},
		{name: '<i class="icon-file-text grey"></i> document2.doc', type: 'item', id : 31},
		{name: '<i class="icon-file-text grey"></i> document3.doc', type: 'item', id : 32},
		{name: '<i class="icon-file-text red"></i> document4.pdf', type: 'item', id : 33},
		{name: '<i class="icon-file-text grey"></i> document5.doc', type: 'item', id : 34}
	]
}

tree_data_2['backup']['additionalParameters'] = {
	'children' : [
		{name: '<i class="icon-archive brown"></i> backup1.zip', type: 'item', id : 35},
		{name: '<i class="icon-archive brown"></i> backup2.zip', type: 'item', id : 36},
		{name: '<i class="icon-archive brown"></i> backup3.zip', type: 'item', id : 37},
		{name: '<i class="icon-archive brown"></i> backup4.zip', type: 'item', id : 38}
	]
}
var treeDataSource2 = new DataSourceTree({data: tree_data_2});