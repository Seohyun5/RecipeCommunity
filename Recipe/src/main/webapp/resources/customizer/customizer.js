/*
 * Template Customizer
 * Copyright 2018 rokaux
 */

jQuery(document).ready(function($) {
	'use strict';

	// Open/close customizer
	$('.customizer-toggle').on('click', function() {
		$('.customizer').toggleClass('open');
	});

	// Switch colors
	$('.customizer-color-switch > a').on('click', function() {
		$('.customizer-color-switch > a').removeClass('active');
		$(this).addClass('active');
		$('.customizer-backdrop').addClass('active');
		setTimeout(function() {
			$('.customizer-backdrop').removeClass('active');
		}, 1000);

		var color = $(this).data('color'),
				currentLink = $('#mainStyles').attr('href'),
				currentLogo = $('.site-logo > img').attr('src'),
				linkParts = currentLink.split('/'),
				logoParts = currentLogo.split('/'),
				lastLinkPart = $(linkParts).get(-1),
				lastLogoPart = $(logoParts).get(-1);
		switch(color) {
			case '00bcd4':
				lastLinkPart = 'styles-00bcd4.min.css';
				lastLogoPart = 'logo-00bcd4.png';
			break;
			case '72bb23':
				lastLinkPart = 'styles-72bb23.min.css';
				lastLogoPart = 'logo-72bb23.png';
			break;
			case 'c37b8e':
				lastLinkPart = 'styles-c37b8e.min.css';
				lastLogoPart = 'logo-c37b8e.png';
				break;
			default:
			lastLinkPart = 'styles.min.css';
			lastLogoPart = 'logo.png';
		}
		linkParts.pop();
		logoParts.pop();
		var newLink = $.map(linkParts, function(val, index) {
			var str = val;
			return str;
		}).join('/');
		var newLogo = $.map(logoParts, function(val, index) {
			var str = val;
			return str;
		}).join('/');
		currentLink = newLink + '/' + lastLinkPart;
		currentLogo = newLogo + '/' + lastLogoPart;
		$('#mainStyles').attr('href', currentLink);
		$('.site-logo > img').attr('src', currentLogo);
	});

	// Switch Header option
	$('#header-option').on('change', function() {
		var currentHeader = $(this).find('option:selected').val(),
				navbar = $('.navbar'),
				navbarH = navbar.outerHeight();
		if(currentHeader === 'static') {
			navbar.removeClass('navbar-sticky');
		} else {
			navbar.addClass('navbar-sticky');
		}
	});

	// Switch Footer option
	$('#footer-option').on('change', function() {
		var currentFooter = $(this).find('option:selected').val(),
		footer = $('.site-footer'),
		column = footer.find('.column'),
		paragraph = column.find('p'),
		copyright = column.find('p.text-xxs'),
		socialBtn = column.find('.social-button'),
		input = footer.find('.input-group');
		if(currentFooter === 'dark') {
			column.addClass('widget-light-skin');
			paragraph.addClass('text-white');
			copyright.addClass('opacity-50');
			socialBtn.addClass('sb-light-skin');
			input.addClass('input-light');
			footer.addClass('footer-dark');
		} else {
			column.removeClass('widget-light-skin');
			paragraph.removeClass('text-white');
			copyright.removeClass('opacity-50');
			socialBtn.removeClass('sb-light-skin');
			input.removeClass('input-light');
			footer.removeClass('footer-dark');
		}
	});

});/*Document Ready End*/
