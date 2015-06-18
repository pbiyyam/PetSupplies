/**
 * 
 */

function store() {
	
};
	
store.prototype.getProduct = function (sku) {
 for (var i = 0; i < this.products.length; i++) {
     if (this.products[i].productId == sku){
    	 return this.products[i]; 
     }
 }
 return null;
};
