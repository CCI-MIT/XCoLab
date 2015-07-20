var ReadyList = [];
$(document).ready(function () {
    if(ReadyList && (ReadyList instanceof Array)) {
        for (var a=0;a<ReadyList.length;a++) {
            ReadyList[a]();
        }
    }
});
ReadyList.push(function() {
    var sEl,
        today=new Date(),
        tickerTime=2,
        BigDay=new Date("October 6, 2015"),
        msPerDay=24*60*60*1000,
        timeLeft=(BigDay.getTime()-today.getTime()),
        e_daysLeft=timeLeft/msPerDay,
        daysLeft=Math.floor(e_daysLeft),
        startDays = daysLeft + 5,

        now = new Date(),
        sVal = startDays,
        tickCount = 0,
        tickerTimeFlip = 0,

        pad = function(val) {
            var ret = '0' + val;
            return ret.substring(ret.length-2);
        },
        initFlip = function(el,val) {
            el.flip = true;
            el.flipCount = 1;
            $(el).addClass('ani1');
            $(el.ani).removeClass('hide');
            el.top.num.innerHTML = val;
        },
        checkFlip = function (el,val) {
            if(el.flip) {
                //	tickerTimeFlip = tickerTimeFlip - 1;
                //	if (tickerTimeFlip < 1) tickerTimeFlip = 1;

                //		if (Math.log(tickerTimeFlip) <= 3.5){
                $(el).removeClass('ani'+el.flipCount);
                el.flipCount += el.flipDir;
                if(el.flipCount > 4) {
                    el.flipDir = -1;
                    el.flipCount = 4;
                    $(el).addClass('ani'+el.flipCount);
                    $(el.ani).removeClass('top').addClass('bottom');
                    el.ani.num.innerHTML = val;

                }
                else if (el.flipCount == 0) {
                    el.flipDir = 1;
                    $(el.ani).removeClass('bottom').addClass('top').addClass('hide');
                    el.btm.num.innerHTML = val;
                    el.flip = false;
                }
                else {
                    $(el).addClass('ani'+el.flipCount);
                }
                //	tickerTimeFlip = (startDays - daysLeft) - (sVal-daysLeft) / (startDays-daysLeft) * 150;

                //	}
            }
        },
        checkTime = function() {
            now = new Date();
            //	tickerTime = tickerTime - 1;
            //if (tickerTime < 1) tickerTime = 1;
            if (!sEl.flip){// && Math.log(tickerTime) <= 1) {
                oldSVal = sVal;
                sVal = (--sVal <= daysLeft)?(++sVal):(sVal);
                if (oldSVal != sVal) initFlip(sEl,pad(sVal));
                //	tickerTime = (startDays - daysLeft) - (sVal-daysLeft) / (startDays-daysLeft) * 150;
            }


        },
        tick = function() {
            checkFlip(sEl,pad(sVal));
            checkTime();
        };
    $('#sec,#min,#hou').each(function() {
        this.ani = $('.front',this).get(0);
        this.ani.num = $('span',this.ani).get(0);
        this.top = $('.top.back',this).get(0);
        this.top.num = $('span',this.top).get(0);
        this.btm = $('.bottom.back',this).get(0);
        this.btm.num = $('span',this.btm).get(0);
        this.flip = false;
        this.flipCount = 0;
        this.flipDir = 1;
        if (this.id == 'sec') {
            sEl = this;
            this.ani.num.innerHTML = this.top.num.innerHTML = this.btm.num.innerHTML = pad(sVal);
        }
    });
    tickTimer = window.setInterval(tick,30);
});
