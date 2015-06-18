'use strict';

function shoppingCart() {
	var cartName = 'MainWebApp';
	this.cartName = cartName;
	 this.clearCart = false;
	 this.checkoutParameters = {};
	 this.items = [];

	 // load items from local storage when initializing
	 this.loadItems();

	 // save items to local storage when unloading
	 var self = this;
	 $(window).unload(function () {
	     if (self.clearCart) {
	         self.clearItems();
	     }
	     self.saveItems();
	     self.clearCart = false;
	 });	
};

//load items from local storage
shoppingCart.prototype.loadItems = function () {
    var items = localStorage != null ? localStorage[this.cartName + "_items"] : null;
    if (items != null && JSON != null) {
        try {
            var items = JSON.parse(items);
            for (var i = 0; i < items.length; i++) {
                var item = items[i];
                if (item.productId != null && item.productName != null && item.productPrice != null && item.productDesc != null && item.quantity != null) {
                    item = new cartItem(item.productId, item.productName, item.productPrice, item.productDesc, item.quantity);
                    this.items.push(item);
                }
            }
        }
        catch (err) {
            // ignore errors while loading...
        }
    }
};

//save items to local storage
shoppingCart.prototype.saveItems = function () {
    if (localStorage != null && JSON != null) {
        localStorage[this.cartName + "_items"] = JSON.stringify(this.items);
    }
};

//clear the cart
shoppingCart.prototype.clearItems = function () {
    this.items = [];
    this.saveItems();
};

//adds an item to the cart
shoppingCart.prototype.addItem = function (sku, name, price,desc, quantity) {
    quantity = this.toNumber(quantity);
    if (quantity != 0) {

        // update quantity for existing item
        var found = false;
        for (var i = 0; i < this.items.length && !found; i++) {
            var item = this.items[i];
            if (item.productId == sku) {
                found = true;
                item.quantity = this.toNumber(item.quantity + quantity);
                if (item.quantity <= 0) {
                    this.items.splice(i, 1);
                }
            }
        }

        // new item, add now
        if (!found) {
            var item = new cartItem(sku, name, price, desc, quantity);
            this.items.push(item);
        }

        // save changes
        this.saveItems();
    }
}

//get the total price for all items currently in the cart
shoppingCart.prototype.getTotalPrice = function (sku) {
    var total = 0;
    for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        if (sku == null || item.productId == sku) {
            total += this.toNumber(item.quantity * item.productPrice);
        }
    }
    return total;
}

// get the total price for all items currently in the cart
shoppingCart.prototype.getTotalCount = function (sku) {
    var count = 0;
    for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        if (sku == null || item.productId == sku) {
            count += this.toNumber(item.quantity);
        }
    }
    return count;
}

//define checkout parameters
shoppingCart.prototype.addCheckoutParameters = function (serviceName, merchantID, options) {

    // check parameters
    if (serviceName != "PayPal" && serviceName != "Google") {
        throw "serviceName must be 'PayPal' or 'Google'.";
    }
    if (merchantID == null) {
        throw "A merchantID is required in order to checkout.";
    }

    // save parameters
    this.checkoutParameters[serviceName] = new checkoutParameters(serviceName, merchantID, options);
}

// check out
shoppingCart.prototype.checkout = function (serviceName, clearCart) {
    // select serviceName if we have to
    if (serviceName == null) {
        var p = this.checkoutParameters[Object.keys(this.checkoutParameters)[0]];
        serviceName = p.serviceName;
    }

    // sanity
    if (serviceName == null) {
        throw "Use the 'addCheckoutParameters' method to define at least one checkout service.";
    }

    // go to work
    var parms = this.checkoutParameters[serviceName];
    if (parms == null) {
        throw "Cannot get checkout parameters for '" + serviceName + "'.";
    }
    switch (parms.serviceName) {
        case "PayPal":
            this.checkoutPayPal(parms, clearCart);
            break;
        case "Google":
            this.checkoutGoogle(parms, clearCart);
            break;
        default:
            throw "Unknown checkout service: " + parms.serviceName;
    }
}

// check out using PayPal
// for details see:
// www.paypal.com/cgi-bin/webscr?cmd=p/pdn/howto_checkout-outside
shoppingCart.prototype.checkoutPayPal = function (parms, clearCart) {
    // global data
    var data = {
        cmd: "_cart",
        business: parms.merchantID,
        upload: "1",
        rm: "2",
        charset: "utf-8"
    };

    //call to placeOrder function
    this.placeOrder(this.items);
    
    // item data
    for (var i = 0; i < this.items.length; i++) {    	
        var item = this.items[i];
        var ctr = i + 1;
        data["item_number_" + ctr] = item.productId;
        data["item_name_" + ctr] = item.paroductName;
        data["quantity_" + ctr] = item.quantity;
        data["amount_" + ctr] = item.productPrice.toFixed(2);
    }

    // build form
    var form = $('<form/></form>');
    form.attr("action", "https://www.paypal.com/cgi-bin/webscr");
    //form.attr("action", "http://localhost:8080/petstoreuser/#/placeOrder.htm");
    form.attr("method", "POST");
    form.attr("style", "display:none;");
    this.addFormFields(form, data);
    this.addFormFields(form, parms.options);
    $("body").append(form);

    // submit form
    this.clearCart = clearCart == null || clearCart;
    form.submit();
    form.remove();
}

//placeorder function call
shoppingCart.prototype.placeOrder = function(items){	
	/*for (var i = 0; i < this.items.length; i++) {
        var item = this.items[i];
        pList.add(new cartProduct(item.productId,item.productName,item.productPrice.toFixed(2),item.productDesc,item.quantity));
    }*/
	return this.items;
};

//utility methods
shoppingCart.prototype.addFormFields = function (form, data) {
    if (data != null) {
        $.each(data, function (name, value) {
            if (value != null) {
                var input = $("<input></input>").attr("type", "hidden").attr("name", name).val(value);
                form.append(input);
            }
        });
    }
}
shoppingCart.prototype.toNumber = function (value) {
    value = value * 1;
    return isNaN(value) ? 0 : value;
}

//----------------------------------------------------------------
//checkout parameters (one per supported payment service)
//
function checkoutParameters(serviceName, merchantID, options) {
 this.serviceName = serviceName;
 this.merchantID = merchantID;
 this.options = options;
}

//----------------------------------------------------------------
//items in the cart
//
function cartItem(sku, name, price, desc, quantity) {
this.productId = sku;
this.productName = name;
this.productPrice = price * 1;
this.productDesc = desc;
this.quantity = quantity * 1;
}

/*//products to post to db
function cartProduct(sku, name, price, desc, quantity) {
	this.productId = sku;
	this.productName = name;
	this.productPrice = price * 1;
	this.productDesc = desc;
	this.quantity = quantity * 1;
}*/

shoppingCart.prototype.go = function(hash){
	//$(window).location.path(hash);
}