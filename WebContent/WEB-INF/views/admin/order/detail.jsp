<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="food.utils.Constants"%>
<fmt:setLocale value="vi-VN" scope="session" /> 
<%@include file="/WEB-INF/views/include/header.jsp"%>
<style>
<!--
#DIV_1 {
	block-size: 25px;
	border-block-end-color: rgb(5, 5, 5);
	border-block-start-color: rgb(5, 5, 5);
	border-collapse: collapse;
	border-inline-end-color: rgb(5, 5, 5);
	border-inline-start-color: rgb(5, 5, 5);
	box-sizing: border-box;
	caret-color: rgb(5, 5, 5);
	color: rgb(5, 5, 5);
	column-rule-color: rgb(5, 5, 5);
	float: left;
	height: 25px;
	inline-size: 85px;
	perspective-origin: 42.5px 12.5px;
	text-align: center;
	text-decoration: none solid rgb(5, 5, 5);
	text-rendering: optimizelegibility;
	text-size-adjust: 100%;
	transform-origin: 42.5px 12.5px;
	width: 85px;
	border: 0px none rgb(5, 5, 5);
	font: 14px/19.6px Quicksand, sans-serif;
	outline: rgb(5, 5, 5) none 0px;
} /*#DIV_1*/
#DIV_1:after {
	block-size: 0px;
	border-block-end-color: rgb(5, 5, 5);
	border-block-start-color: rgb(5, 5, 5);
	border-collapse: collapse;
	border-inline-end-color: rgb(5, 5, 5);
	border-inline-start-color: rgb(5, 5, 5);
	box-sizing: border-box;
	caret-color: rgb(5, 5, 5);
	clear: both;
	color: rgb(5, 5, 5);
	column-rule-color: rgb(5, 5, 5);
	content: '"' '"';
	display: table;
	height: 0px;
	inline-size: 0px;
	perspective-origin: 0px 0px;
	text-align: center;
	text-decoration: none solid rgb(5, 5, 5);
	text-rendering: optimizelegibility;
	text-size-adjust: 100%;
	transform-origin: 0px 0px;
	width: 0px;
	border: 0px none rgb(5, 5, 5);
	font: 14px/19.6px Quicksand, sans-serif;
	outline: rgb(5, 5, 5) none 0px;
} /*#DIV_1:after*/
#DIV_1:before {
	block-size: 0px;
	border-block-end-color: rgb(5, 5, 5);
	border-block-start-color: rgb(5, 5, 5);
	border-collapse: collapse;
	border-inline-end-color: rgb(5, 5, 5);
	border-inline-start-color: rgb(5, 5, 5);
	box-sizing: border-box;
	caret-color: rgb(5, 5, 5);
	color: rgb(5, 5, 5);
	column-rule-color: rgb(5, 5, 5);
	content: '"' '"';
	display: table;
	height: 0px;
	inline-size: 0px;
	perspective-origin: 0px 0px;
	text-align: center;
	text-decoration: none solid rgb(5, 5, 5);
	text-rendering: optimizelegibility;
	text-size-adjust: 100%;
	transform-origin: 0px 0px;
	width: 0px;
	border: 0px none rgb(5, 5, 5);
	font: 14px/19.6px Quicksand, sans-serif;
	outline: rgb(5, 5, 5) none 0px;
} /*#DIV_1:before*/
#BUTTON_2, #BUTTON_4 {
	appearance: none;
	block-size: 25px;
	border-block-end-color: rgb(218, 219, 221);
	border-block-end-style: solid;
	border-block-end-width: 1px;
	border-block-start-color: rgb(218, 219, 221);
	border-block-start-style: solid;
	border-block-start-width: 1px;
	border-collapse: collapse;
	border-inline-end-color: rgb(218, 219, 221);
	border-inline-end-style: solid;
	border-inline-end-width: 1px;
	border-inline-start-color: rgb(218, 219, 221);
	border-inline-start-style: solid;
	border-inline-start-width: 1px;
	caret-color: rgb(171, 175, 178);
	color: rgb(171, 175, 178);
	column-rule-color: rgb(171, 175, 178);
	cursor: pointer;
	display: block;
	float: left;
	height: 25px;
	inline-size: 25px;
	padding-block-end: 0px;
	padding-block-start: 0px;
	padding-inline-end: 0px;
	padding-inline-start: 0px;
	perspective-origin: 12.5px 12.5px;
	text-decoration: none solid rgb(171, 175, 178);
	text-size-adjust: 100%;
	transform-origin: 12.5px 12.5px;
	width: 25px;
	background: rgb(255, 255, 255) none repeat scroll 0% 0%/auto padding-box
		border-box;
	border: 1px solid rgb(218, 219, 221);
	font: 500 14px/19.6px Quicksand, sans-serif;
	outline: rgb(171, 175, 178) none 0px;
	padding: 0px;
} /*#BUTTON_2, #BUTTON_4*/
.BUTTON_2:after, .BUTTON_4:after {
	border-block-end-color: rgb(171, 175, 178);
	border-block-start-color: rgb(171, 175, 178);
	border-collapse: collapse;
	border-inline-end-color: rgb(171, 175, 178);
	border-inline-start-color: rgb(171, 175, 178);
	box-sizing: border-box;
	caret-color: rgb(171, 175, 178);
	color: rgb(171, 175, 178);
	column-rule-color: rgb(171, 175, 178);
	cursor: pointer;
	text-decoration: none solid rgb(171, 175, 178);
	text-size-adjust: 100%;
	border: 0px none rgb(171, 175, 178);
	font: 500 14px/19.6px Quicksand, sans-serif;
	outline: rgb(171, 175, 178) none 0px;
} /*#BUTTON_2:after, #BUTTON_4:after*/
#BUTTON_2:before, #BUTTON_4:before {
	border-block-end-color: rgb(171, 175, 178);
	border-block-start-color: rgb(171, 175, 178);
	border-collapse: collapse;
	border-inline-end-color: rgb(171, 175, 178);
	border-inline-start-color: rgb(171, 175, 178);
	box-sizing: border-box;
	caret-color: rgb(171, 175, 178);
	color: rgb(171, 175, 178);
	column-rule-color: rgb(171, 175, 178);
	cursor: pointer;
	text-decoration: none solid rgb(171, 175, 178);
	text-size-adjust: 100%;
	border: 0px none rgb(171, 175, 178);
	font: 500 14px/19.6px Quicksand, sans-serif;
	outline: rgb(171, 175, 178) none 0px;
} /*#BUTTON_2:before, #BUTTON_4:before*/
#INPUT_3 {
	appearance: none;
	block-size: 25px;
	border-block-end-color: rgb(218, 219, 221);
	border-block-end-style: solid;
	border-block-end-width: 1px;
	border-block-start-color: rgb(218, 219, 221);
	border-block-start-style: solid;
	border-block-start-width: 1px;
	border-collapse: collapse;
	border-inline-end-color: rgb(5, 5, 5);
	border-inline-end-style: none;
	border-inline-end-width: 0px;
	border-inline-start-color: rgb(5, 5, 5);
	border-inline-start-style: none;
	border-inline-start-width: 0px;
	caret-color: rgb(5, 5, 5);
	color: rgb(5, 5, 5);
	column-rule-color: rgb(5, 5, 5);
	display: block;
	float: left;
	height: 25px;
	inline-size: 35px;
	padding-block-end: 0px;
	padding-block-start: 0px;
	padding-inline-end: 0px;
	padding-inline-start: 0px;
	perspective-origin: 17.5px 12.5px;
	text-align: center;
	text-decoration: none solid rgb(5, 5, 5);
	text-size-adjust: 100%;
	transform-origin: 17.5px 12.5px;
	width: 35px;
	background: rgb(237, 237, 237) none repeat scroll 0% 0%/auto padding-box
		border-box;
	border-top: 1px solid rgb(218, 219, 221);
	border-right: 0px none rgb(5, 5, 5);
	border-bottom: 1px solid rgb(218, 219, 221);
	border-left: 0px none rgb(5, 5, 5);
	font: 500 15px/21px Quicksand, sans-serif;
	outline: rgb(5, 5, 5) none 0px;
	padding: 0px;
} /*#INPUT_3*/
#INPUT_3:after {
	border-block-end-color: rgb(5, 5, 5);
	border-block-start-color: rgb(5, 5, 5);
	border-collapse: collapse;
	border-inline-end-color: rgb(5, 5, 5);
	border-inline-start-color: rgb(5, 5, 5);
	box-sizing: border-box;
	caret-color: rgb(5, 5, 5);
	color: rgb(5, 5, 5);
	column-rule-color: rgb(5, 5, 5);
	text-align: center;
	text-decoration: none solid rgb(5, 5, 5);
	text-size-adjust: 100%;
	border: 0px none rgb(5, 5, 5);
	font: 500 15px/21px Quicksand, sans-serif;
	outline: rgb(5, 5, 5) none 0px;
} /*#INPUT_3:after*/
#INPUT_3:before {
	border-block-end-color: rgb(5, 5, 5);
	border-block-start-color: rgb(5, 5, 5);
	border-collapse: collapse;
	border-inline-end-color: rgb(5, 5, 5);
	border-inline-start-color: rgb(5, 5, 5);
	box-sizing: border-box;
	caret-color: rgb(5, 5, 5);
	color: rgb(5, 5, 5);
	column-rule-color: rgb(5, 5, 5);
	text-align: center;
	text-decoration: none solid rgb(5, 5, 5);
	text-size-adjust: 100%;
	border: 0px none rgb(5, 5, 5);
	font: 500 15px/21px Quicksand, sans-serif;
	outline: rgb(5, 5, 5) none 0px;
} /*#INPUT_3:before*/
#IMG_1 {
	block-size: 100px;
	border-block-end-color: rgb(5, 5, 5);
	border-block-start-color: rgb(5, 5, 5);
	border-collapse: collapse;
	border-inline-end-color: rgb(5, 5, 5);
	border-inline-start-color: rgb(5, 5, 5);
	box-sizing: border-box;
	caret-color: rgb(5, 5, 5);
	color: rgb(5, 5, 5);
	column-rule-color: rgb(5, 5, 5);
	cursor: pointer;
	height: 100px;
	inline-size: 100px;
	margin-block-end: 10px;
	max-inline-size: 100px;
	max-width: 100px;
	perspective-origin: 50px 50px;
	text-align: center;
	text-decoration: none solid rgb(5, 5, 5);
	text-rendering: optimizelegibility;
	text-size-adjust: 100%;
	transform-origin: 50px 50px;
	vertical-align: middle;
	width: 100px;
	border: 0px none rgb(5, 5, 5);
	font: 14px/19.6px Quicksand, sans-serif;
	margin: 0px 0px 10px;
	outline: rgb(5, 5, 5) none 0px;
} /*#IMG_1*/
#IMG_1:after {
	border-block-end-color: rgb(5, 5, 5);
	border-block-start-color: rgb(5, 5, 5);
	border-collapse: collapse;
	border-inline-end-color: rgb(5, 5, 5);
	border-inline-start-color: rgb(5, 5, 5);
	box-sizing: border-box;
	caret-color: rgb(5, 5, 5);
	color: rgb(5, 5, 5);
	column-rule-color: rgb(5, 5, 5);
	cursor: pointer;
	text-align: center;
	text-decoration: none solid rgb(5, 5, 5);
	text-rendering: optimizelegibility;
	text-size-adjust: 100%;
	border: 0px none rgb(5, 5, 5);
	font: 14px/19.6px Quicksand, sans-serif;
	outline: rgb(5, 5, 5) none 0px;
} /*#IMG_1:after*/
#IMG_1:before {
	border-block-end-color: rgb(5, 5, 5);
	border-block-start-color: rgb(5, 5, 5);
	border-collapse: collapse;
	border-inline-end-color: rgb(5, 5, 5);
	border-inline-start-color: rgb(5, 5, 5);
	box-sizing: border-box;
	caret-color: rgb(5, 5, 5);
	color: rgb(5, 5, 5);
	column-rule-color: rgb(5, 5, 5);
	cursor: pointer;
	text-align: center;
	text-decoration: none solid rgb(5, 5, 5);
	text-rendering: optimizelegibility;
	text-size-adjust: 100%;
	border: 0px none rgb(5, 5, 5);
	font: 14px/19.6px Quicksand, sans-serif;
	outline: rgb(5, 5, 5) none 0px;
} /*#IMG_1:before*/
#P_1 {
    block-size: 26.4px;
    border-block-end-color: rgb(5, 5, 5);
    border-block-start-color: rgb(5, 5, 5);
    border-inline-end-color: rgb(5, 5, 5);
    border-inline-start-color: rgb(5, 5, 5);
    box-sizing: border-box;
    caret-color: rgb(5, 5, 5);
    color: rgb(5, 5, 5);
    column-rule-color: rgb(5, 5, 5);
    height: 26.4px;
    inline-size: 850.8px;
    margin-block-end: 40px;
    margin-block-start: 0px;
    perspective-origin: 425.4px 13.2px;
    text-align: right;
    text-decoration: none solid rgb(5, 5, 5);
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    transform-origin: 425.4px 13.2px;
    width: 850.8px;
    border: 0px none rgb(5, 5, 5);
    font: 500 16px / 21px Quicksand, sans-serif;
    margin: 0px 0px 40px;
    outline: rgb(5, 5, 5) none 0px;
    text-align: right;
}/*#P_1*/

#P_1:after {
    border-block-end-color: rgb(5, 5, 5);
    border-block-start-color: rgb(5, 5, 5);
    border-inline-end-color: rgb(5, 5, 5);
    border-inline-start-color: rgb(5, 5, 5);
    box-sizing: border-box;
    caret-color: rgb(5, 5, 5);
    color: rgb(5, 5, 5);
    column-rule-color: rgb(5, 5, 5);
    text-align: right;
    text-decoration: none solid rgb(5, 5, 5);
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    border: 0px none rgb(5, 5, 5);
    font: 500 16px / 21px Quicksand, sans-serif;
    outline: rgb(5, 5, 5) none 0px;
}/*#P_1:after*/

#P_1:before {
    border-block-end-color: rgb(5, 5, 5);
    border-block-start-color: rgb(5, 5, 5);
    border-inline-end-color: rgb(5, 5, 5);
    border-inline-start-color: rgb(5, 5, 5);
    box-sizing: border-box;
    caret-color: rgb(5, 5, 5);
    color: rgb(5, 5, 5);
    column-rule-color: rgb(5, 5, 5);
    text-align: right;
    text-decoration: none solid rgb(5, 5, 5);
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    border: 0px none rgb(5, 5, 5);
    font: 500 16px / 21px Quicksand, sans-serif;
    outline: rgb(5, 5, 5) none 0px;
}/*#P_1:before*/

#SPAN_2 {
    border-block-end-color: rgb(5, 5, 5);
    border-block-start-color: rgb(5, 5, 5);
    border-inline-end-color: rgb(5, 5, 5);
    border-inline-start-color: rgb(5, 5, 5);
    box-sizing: border-box;
    caret-color: rgb(5, 5, 5);
    color: rgb(5, 5, 5);
    column-rule-color: rgb(5, 5, 5);
    margin-inline-start: 7px;
    perspective-origin: 0px 0px;
    text-align: right;
    text-decoration: none solid rgb(5, 5, 5);
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    transform-origin: 0px 0px;
    border: 0px none rgb(5, 5, 5);
    font: 500 30px / 21px Quicksand, sans-serif;
    margin: 0px 0px 0px 7px;
    outline: rgb(5, 5, 5) none 0px;
}/*#SPAN_2*/

#SPAN_2:after {
    border-block-end-color: rgb(5, 5, 5);
    border-block-start-color: rgb(5, 5, 5);
    border-inline-end-color: rgb(5, 5, 5);
    border-inline-start-color: rgb(5, 5, 5);
    box-sizing: border-box;
    caret-color: rgb(5, 5, 5);
    color: rgb(5, 5, 5);
    column-rule-color: rgb(5, 5, 5);
    text-align: right;
    text-decoration: none solid rgb(5, 5, 5);
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    border: 0px none rgb(5, 5, 5);
    font: 500 30px / 21px Quicksand, sans-serif;
    outline: rgb(5, 5, 5) none 0px;
}/*#SPAN_2:after*/

#SPAN_2:before {
    border-block-end-color: rgb(5, 5, 5);
    border-block-start-color: rgb(5, 5, 5);
    border-inline-end-color: rgb(5, 5, 5);
    border-inline-start-color: rgb(5, 5, 5);
    box-sizing: border-box;
    caret-color: rgb(5, 5, 5);
    color: rgb(5, 5, 5);
    column-rule-color: rgb(5, 5, 5);
    text-align: right;
    text-decoration: none solid rgb(5, 5, 5);
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    border: 0px none rgb(5, 5, 5);
    font: 500 30px / 21px Quicksand, sans-serif;
    outline: rgb(5, 5, 5) none 0px;
}/*#SPAN_2:before*/

#B_3 {
    border-block-end-color: rgb(5, 5, 5);
    border-block-start-color: rgb(5, 5, 5);
    border-inline-end-color: rgb(5, 5, 5);
    border-inline-start-color: rgb(5, 5, 5);
    box-sizing: border-box;
    caret-color: rgb(5, 5, 5);
    color: rgb(5, 5, 5);
    column-rule-color: rgb(5, 5, 5);
    perspective-origin: 0px 0px;
    text-align: right;
    text-decoration: none solid rgb(5, 5, 5);
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    transform-origin: 0px 0px;
    border: 0px none rgb(5, 5, 5);
    font: 700 30px / 21px Quicksand, sans-serif;
    outline: rgb(5, 5, 5) none 0px;
}/*#B_3*/

#B_3:after {
    border-block-end-color: rgb(5, 5, 5);
    border-block-start-color: rgb(5, 5, 5);
    border-inline-end-color: rgb(5, 5, 5);
    border-inline-start-color: rgb(5, 5, 5);
    box-sizing: border-box;
    caret-color: rgb(5, 5, 5);
    color: rgb(5, 5, 5);
    column-rule-color: rgb(5, 5, 5);
    text-align: right;
    text-decoration: none solid rgb(5, 5, 5);
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    border: 0px none rgb(5, 5, 5);
    font: 700 30px / 21px Quicksand, sans-serif;
    outline: rgb(5, 5, 5) none 0px;
}/*#B_3:after*/

#B_3:before {
    border-block-end-color: rgb(5, 5, 5);
    border-block-start-color: rgb(5, 5, 5);
    border-inline-end-color: rgb(5, 5, 5);
    border-inline-start-color: rgb(5, 5, 5);
    box-sizing: border-box;
    caret-color: rgb(5, 5, 5);
    color: rgb(5, 5, 5);
    column-rule-color: rgb(5, 5, 5);
    text-align: right;
    text-decoration: none solid rgb(5, 5, 5);
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    border: 0px none rgb(5, 5, 5);
    font: 700 30px / 21px Quicksand, sans-serif;
    outline: rgb(5, 5, 5) none 0px;
}/*#B_3:before*/
#H1_1 {
    block-size: 36px;
    box-sizing: border-box;
    height: 36px;
    inline-size: 880.8px;
    margin-block-end: 10px;
    margin-block-start: 0px;
    perspective-origin: 440.4px 18px;
    text-align: right;
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    transform-origin: 440.4px 18px;
    width: 880.8px;
    font: 700 30px / 36px Quicksand, sans-serif;
    margin: 0px 0px 10px;
}/*#H1_1*/

#H1_1:after {
    box-sizing: border-box;
    text-align: center;
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    font: 700 30px / 36px Quicksand, sans-serif;
}/*#H1_1:after*/

#H1_1:before {
    box-sizing: border-box;
    text-align: center;
    text-rendering: optimizelegibility;
    text-size-adjust: 100%;
    font: 700 30px / 36px Quicksand, sans-serif;
}/*#H1_1:before*/
#INPUT_1 {
    appearance: none;
    block-size: 43.1px;
    border-block-end-color: rgb(51, 51, 51);
    border-block-end-style: none;
    border-block-end-width: 0px;
    border-block-start-color: rgb(51, 51, 51);
    border-block-start-style: none;
    border-block-start-width: 0px;
    border-end-end-radius: 4px;
    border-end-start-radius: 4px;
    border-inline-end-color: rgb(51, 51, 51);
    border-inline-end-style: none;
    border-inline-end-width: 0px;
    border-inline-start-color: rgb(51, 51, 51);
    border-inline-start-style: none;
    border-inline-start-width: 0px;
    border-start-end-radius: 4px;
    border-start-start-radius: 4px;
    box-shadow: rgb(217, 217, 217) 0px 0px 0px 1px;
    caret-color: rgb(51, 51, 51);
    color: rgb(51, 51, 51);
    column-rule-color: rgb(51, 51, 51);
    display: block;
    height: 43.1px;
    inline-size: 418.05px;
    overflow-wrap: break-word;
    padding-block-end: 13.16px;
    padding-block-start: 13.16px;
    padding-inline-end: 39.2px;
    padding-inline-start: 11.2px;
    perspective-origin: 209.025px 21.55px;
    text-decoration: none solid rgb(51, 51, 51);
    transform-origin: 209.025px 21.55px;
    width: 418.05px;
    border: 0px none rgb(51, 51, 51);
    border-radius: 4px;
    font: 14px "Helvetica Neue", sans-serif;
    outline: rgb(51, 51, 51) none 0px;
    padding: 13.16px 39.2px 13.16px 11.2px;
    transition: all 0.2s ease-out 0s;
}/*#INPUT_1*/

#INPUT_1:after {
    border-block-end-color: rgb(51, 51, 51);
    border-block-start-color: rgb(51, 51, 51);
    border-inline-end-color: rgb(51, 51, 51);
    border-inline-start-color: rgb(51, 51, 51);
    caret-color: rgb(51, 51, 51);
    color: rgb(51, 51, 51);
    column-rule-color: rgb(51, 51, 51);
    overflow-wrap: break-word;
    text-decoration: none solid rgb(51, 51, 51);
    border: 0px none rgb(51, 51, 51);
    font: 14px "Helvetica Neue", sans-serif;
    outline: rgb(51, 51, 51) none 0px;
}/*#INPUT_1:after*/

#INPUT_1:before {
    border-block-end-color: rgb(51, 51, 51);
    border-block-start-color: rgb(51, 51, 51);
    border-inline-end-color: rgb(51, 51, 51);
    border-inline-start-color: rgb(51, 51, 51);
    caret-color: rgb(51, 51, 51);
    color: rgb(51, 51, 51);
    column-rule-color: rgb(51, 51, 51);
    overflow-wrap: break-word;
    text-decoration: none solid rgb(51, 51, 51);
    border: 0px none rgb(51, 51, 51);
    font: 14px "Helvetica Neue", sans-serif;
    outline: rgb(51, 51, 51) none 0px;
}/*#INPUT_1:before*/
a.disabled {
  pointer-events: none;
  cursor: default;
}



-->
</style>
<div>
		<table class="table table-hover" >
			<thead>
				<tr>
					<th scope="col">Tên món</th>
					<th scope="col">Số lượng</th>
					<th scope="col">Đơn giá</th>
				</tr>
			</thead>
		<c:forEach var="o" items="${orderDetail}">
			<tbody>
				<tr>
					<td><p>${o.food.name}</p>
					<td>
						<p>${o.amount}</p>
					</td>
					<td><fmt:formatNumber value="${o.price}" type="currency"
							currencySymbol="đ" minFractionDigits="0" /></td>
				</tr>
			</tbody>
		</c:forEach>
	</table>
	
	<div>	
		<p id="P_1">
			Tổng tiền: <span id="SPAN_2"><b id="B_3"><fmt:formatNumber value="${total}" type="currency"
									currencySymbol="đ" minFractionDigits="0" /></b></span>
		</p>
	</div>
	
	<div style="text-align: center;">
		<c:if test="${orderDetail[0].order.status == 1}"><a  class="btn btn-primary" href="admin/order/status.htm?orderId=${orderDetail[0].order.orderId}&status=2">Hoàn thành</a> </c:if>
		<c:if test="${orderDetail[0].order.status == 0}"><a  class="btn btn-primary" href="admin/order/status.htm?orderId=${orderDetail[0].order.orderId}&status=1">Xác nhận</a>
				      								<a  class="btn btn-danger" href="admin/order/status.htm?orderId=${orderDetail[0].order.orderId}&status=3">Hủy đơn</a> </c:if>
	</div>
</div>




<%@include file="/WEB-INF/views/include/footer.jsp"%>