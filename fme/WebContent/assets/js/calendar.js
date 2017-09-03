!function($) {
    "use strict";
$(function(){

   
	  ///Calendar
	  (function () {
	    $('#cw-body').fullCalendar({
                                                                                                                   
                                                         header: {
                left: 'prev, next',
                center: 'title',
                right: 'month, agendaWeek, agendaDay'
            },
            buttonIcons: {
                prev: 'none fa fa-arrow-left',
                next: 'none fa fa-arrow-right',
                prevYear: 'none fa fa-arrow-left',
                nextYear: 'none fa fa-arrow-right'
            },
                                                          defaultDate: '2017-03-23',
                                                          editable: true,
                                                          events: [
                                                              {
                                                                  title: 'All Day',
                                                                  start: '2017-03-23',
                                                                  className: 'bgm-cyan'
                                                              },
                                                              {
                                                                  title: 'Long',
                                                                  start: '2017-03-23',
                                                                  end: '2018-06-8',
                                                                  className: 'bgm-orange'
                                                              },
                                                              {
                                                                  id: 999,
                                                                  title: 'Repeat',
                                                                  start: '2017-03-23',
                                                                  className: 'bgm-lightgreen'
                                                              },
                                                              {
                                                                  id: 999,
                                                                  title: 'Repeat',
                                                                  start: '2017-03-23',
                                                                  className: 'bgm-lightblue'
                                                              },
                                                              {
                                                                  title: 'Meet',
                                                                  start: '2017-03-23',
                                                                  end: '2018-06-12',
                                                                  className: 'bgm-green'
                                                              },
                                                              {
                                                                  title: 'Lunch',
                                                                  start: '2017-03-23',
                                                                  className: 'bgm-cyan'
                                                              },
                                                              {
                                                                  title: 'Birthday',
                                                                  start: '2018-03-23',
                                                                  className: 'bgm-amber'
                                                              },
                                                              {
                                                                  title: 'Google',
                                                                  url: 'http://google.com/',
                                                                  start: '2017-03-23',
                                                                  className: 'bgm-amber'
                                                              }
                                                          ]
                                                      });
		 })();
	
});

	

	  
	}(window.jQuery);	