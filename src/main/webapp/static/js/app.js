;
$(function() {
	var init = function() {
		initBuyBtn();
		$('#addToCart').click(addProductToCart);
		$('#addProductPopup .count').change(calculateCost);
		$('#loadMore').click(loadMoreProducts);
		$('#loadMoreOrders').click(loadMoreOrders);
		initSearchForm();
		$('#goSearch').click(goSearch);
		$('.remove-product').click(removeProductFromCart);
	};

	var showAddProductPopup = function() {
		var idProduct = $(this).attr('data-id-product');
		var product = $('#product' + idProduct);
		$('#addProductPopup').attr('data-id-product', idProduct);
		$('#addProductPopup .product-image').attr('src',
				product.find('.thumbnail img').attr('src'));
		$('#addProductPopup .name').text(product.find('.name').text());
		var price = product.find('.price').text();
		$('#addProductPopup .price').text(price);
		$('#addProductPopup .category').text(product.find('.category').text());
		$('#addProductPopup .producer').text(product.find('.producer').text());
		$('#addProductPopup .count').val(1);
		$('#addProductPopup .cost').text(price);
		$('#addToCart').removeClass('hidden');
		$('#addToCartIndicator').addClass('hidden');
		$('#addProductPopup').modal({
			show : true
		});
	};
	var initBuyBtn = function() {
		$('.buy-btn').click(showAddProductPopup);
	};
	var addProductToCart = function() {
		var idProduct = $('#addProductPopup').attr('data-id-product');
		var count = $('#addProductPopup .count').val();
		var btn = $('#addToCart');
		convertBtnToLoader(btn, 'btn-primary');

		$.ajax({
			url : '/WebShop/ajax/json/product/add',
			method : 'POST',
			data : {
				idProduct : idProduct,
				count : count
			},
			success : function(data) {
				$('#currentShoppingCart .total-count').text(data.totalCount);
				$('#currentShoppingCart .total-cost').text(data.totalCost);
				convertLoaderToBtn(btn, 'btn-primary', addProductToCart);
				$('#currentShoppingCart').removeClass('hidden');
				$('#addProductPopup').modal('hide');
			},
			error : function(xhr) {
				convertLoaderToBtn(btn, 'btn-primary', addProductToCart);
				if (xhr.status == 400) {
					alert(xhr.responseJSON.message);
				} else {
					alert('Error');
				}
			}

		});

	};
	var calculateCost = function() {
		var priceStr = $('#addProductPopup .price').text();
		var price = parseFloat(priceStr.replace('$', ' '));
		var count = parseInt($('#addProductPopup .count').val());
		var min = parseInt($('#addProductPopup .count').attr('min'));
		var max = parseInt($('#addProductPopup .count').attr('max'));
		if (count >= min && count <= max) {
			var cost = price * count;
			$('#addProductPopup .cost').text('$ ' + cost);
		} else {
			$('#addProductPopup .count').val(1);
			$('#addProductPopup .cost').text(priceStr);
		}
	};

	var convertBtnToLoader = function(btn, btnClass) {
		btn.removeClass(btnClass);
		btn.removeClass('btn');
		btn.addClass('load-indicator');
		var text = btn.text();
		btn.text('');
		btn.attr('data-btn-text', text);
		btn.off('click');
	};

	var convertLoaderToBtn = function(btn, btnClass, actionClick) {
		btn.removeClass('load-indicator');
		btn.addClass('btn');
		btn.addClass(btnClass);
		btn.text(btn.attr('data-btn-text'));
		btn.removeAttr('data-btn-text');
		btn.click(actionClick);
	};

	var loadMoreProducts = function() {
		var btn = $('#loadMore');
		convertBtnToLoader(btn, 'btn-success');

		var pageNumber = parseInt($('#productList').attr('data-page-num'));
		var url = '/WebShop/ajax/html/more/' + location.pathname.substring(9)
				+ '?page=' + (pageNumber + 1) + '&'
				+ location.search.substring(1);

		$.ajax({
			url : url,
			success : function(html) {
				$('#productList .row').append(html);
				pageNumber++;
				var pageCount = parseInt($('#productList').attr(
						'data-page-count'));

				if (pageNumber < pageCount) {
					$('#productList').attr('data-page-num', pageNumber);
					convertLoaderToBtn(btn, 'btn-success', loadMoreProducts);
				} else {
					btn.remove();
				}
				initBuyBtn();
			},
			error : function(xhr) {
				convertLoaderToBtn(btn, 'btn-success', loadMoreProducts);
				if (xhr.status == 401) {
					window.location.href = "/WebShop/sign-in";
				} else {
					alert('Error');
				}
			}

		});
	};
	var loadMoreOrders = function() {
		var btn = $('#loadMoreOrders');
		convertBtnToLoader(btn, 'btn-success');

		var pageNumber = parseInt($('#table-orders').attr('data-page-num'));
		var url = '/WebShop/ajax/html/more/' + location.pathname.substring(9)
				+ '?page=' + (pageNumber + 1) + '&'
				+ location.search.substring(1);

		$.ajax({
			url : url,
			success : function(html) {
				$('#table-orders').append(html);
				pageNumber++;
				var pageCount = parseInt($('#table-orders').attr(
						'data-page-count'));

				if (pageNumber < pageCount) {
					$('#table-orders').attr('data-page-num', pageNumber);
					convertLoaderToBtn(btn, 'btn-success', loadMoreOrders);
				} else {
					btn.remove();
				}
			},
			error : function(xhr) {
				convertLoaderToBtn(btn, 'btn-success', loadMoreOrders);
				if (xhr.status == 401) {
					window.location.href = "/WebShop/sign-in";
				} else {
					alert('Error');
				}
			}

		});
	};
	var initSearchForm = function() {
		$('#allCategories').click(
				function() {
					$('.categories .search-option').prop('checked',
							$(this).is(':checked'));
				});
		$('.categories .search-option').click(function() {
			$('#allCategories').prop('checked', false);
		});
		$('#allProducers').click(
				function() {
					$('.producers .search-option').prop('checked',
							$(this).is(':checked'));
				});
		$('.producers .search-option').click(function() {
			$('#allProducers').prop('checked', false);
		});
	};
	var goSearch = function() {
		var isAllSelected = function(selector) {
			var unchecked = 0;
			$(selector).each(function(index, value) {
				if (!$(value).is(':checked')) {
					unchecked++;
				}
			});
			return unchecked === 0;
		};
		if (isAllSelected('.categories .search-option')) {
			$('.categories .search-option').prop('checked', false);
		}
		if (isAllSelected('.producers .search-option')) {
			$('.producers .search-option').prop('checked', false);
		}
		$('form.search').submit();
	};
	var confirm = function(msg, okFunction) {
		if (window.confirm(msg)) {
			okFunction();
		}
	};
	var removeProductFromCart = function() {
		var btn = $(this);
		confirm('Are you sure?', function() {
			executeRemoveProduct(btn);
		});
	};
	var refreshTotalCost = function() {
		var total = 0;
		$('#shoppingCart .item').each(
				function(index, value) {
					var count = parseInt($(value).find('.count').text());
					var price = parseFloat($(value).find('.price').text()
							.replace('$', ' '));
					var val = price * count;
					total = total + val;
				});
		$('#shoppingCart .total').text(total + ' $');
	};
	var executeRemoveProduct = function(btn) {
		var idProduct = btn.attr('data-id-product');
		var count = btn.attr('data-count');
		btn.removeClass('btn-danger');
		btn.removeClass('btn');
		btn.addClass('load-indicator');
		var text = btn.text();
		btn.text('');
		btn.off('click');

		$.ajax({
			url : '/WebShop/ajax/json/product/remove',
			method : 'POST',
			data : {
				idProduct : idProduct,
				count : count
			},
			success : function(data) {
				if (data.totalCount === 0) {
					window.location.href = '/WebShop/products';
				} else {
					var prevCount = parseInt($(
							'#product' + idProduct + ' .count').text());
					var remCount = parseInt(count);
					if (remCount === prevCount) {
						$('#product' + idProduct).remove();

						//
						if ($('#shoppingCart .item').length === 0) {
							window.location.href = '/WebShop/products';
						}
						//
					} else {
						btn.removeClass('load-indicator');
						btn.addClass('btn-danger');
						btn.addClass('btn');
						btn.text(text);
						btn.click(removeProductFromCart);
						$('#product' + idProduct + ' td .all').attr('data-count', prevCount - remCount);
						$('#product' + idProduct + ' .count').text(
								prevCount - remCount);
						if (prevCount - remCount == 1) {
							$('#product' + idProduct + ' a.remove-product.all')
									.remove();
						}
					}
					$('#currentShoppingCart .total-count')
							.text(data.totalCount);
					$('#currentShoppingCart .total-cost').text(data.totalCost);
					refreshTotalCost();
				}
			},
			error : function(data) {
				alert('Error');
			}
		});

	};
	init();
});