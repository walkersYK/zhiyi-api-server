<template>
    <div class="">
        <div class="affix ">
            <div class="affix-content">
                <div class="affix-content__top">
                    <span class="icon-search"></span>
                    <div class="text">搜索</div>
                </div>
                <div class="affix-content__bottom">
                    <div class="assix-suggest">
                        <div class="text">咨询</div>
                        <div class="text">建议</div>
                    </div>
                    <div class="border"></div>
                    <div class="affix-applet">
                        <div class="text">体验</div>
                        <div class="text">中心</div>
                    </div>
                </div>
                <div class="affix-panel affix-panel__active affix-panel__search">
                    <div class="affix-panel-content__suggest ">
                        <div class="panel-item panel-item-consult">
                            <div class="img-wrap">
                                <img src="/static/img/layout/affix/icon_message.png" alt="">
                            </div>
                            <span class="text">售前在线咨询</span>
                        </div>
                        <div class="panel-item panel-item-call">
                            <div class="img-wrap">
                                <img src="/static/img/layout/affix/icon_wechat.png" alt="">
                            </div>
                            <div class="panel-item-call__right">
                                <div class="call-text">加入官方社群</div>
                                <img src="/static/img/layout/affix/code.png" alt="" class="qrcode">
                            </div>
                        </div>
                        <div class="panel-item panel-item-consult">
                            <div class="img-wrap">
                                <img src="/static/img/layout/affix/icon_edit.png" alt="">
                            </div>
                            <span class="text">意见反馈</span>
                        </div>
                    </div>
                    <div class="affix-panel-content__search affix-panel-content__search__show">
                        <div class="search-panel">
                            <div class="search-panel__top">
                                <div class="ant-select ant-select-auto-complete css-1i536d8 ant-select-single ant-select-customize-input ant-select-show-search" maxlength="100">
                                    <div class="ant-select-selector">
                                        <span class="ant-select-selection-search">
                                            <span class="ant-input-group-wrapper ant-select-selection-search-input css-1i536d8" style="height:50px">
                                                <span class="ant-input-wrapper ant-input-group css-1i536d8">
                                                    <span class="ant-input-group-addon">
                                                        <span class="search-panel__icon icon-search">

                                                        </span>
                                                    </span>
                                                    <span class="ant-input-affix-wrapper css-1i536d8">
                                                        <input type="search" autocomplete="off" placeholder="输入您想搜索的内容" role="combobox" aria-haspopup="listbox" aria-owns="rc_select_0_list" aria-autocomplete="list" aria-controls="rc_select_0_list" aria-activedescendant="rc_select_0_list_0" value="" maxlength="100" class="ant-input css-1i536d8" id="rc_select_0" aria-expanded="false" autofocus="">
                                                        <span class="ant-input-suffix">
                                                            <span class="ant-input-clear-icon ant-input-clear-icon-hidden" role="button" tabindex="-1">
                                                                <span class="search-panel__icon icon-close"></span>
                                                            </span>
                                                        </span>
                                                    </span>
                                                </span>
                                            </span>
                                        </span>
                                        <span class="ant-select-selection-placeholder"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="search-panel__bottom">
                                <div class="search-panel__item search-panel__history " style="display: none;">
                                    <div class="search-panel__item_title">
                                        <span class="search-panel__icon icon-history"></span>
                                        搜索历史
                                    </div>
                                    <div class="search-panel__item_con"></div>
                                </div>
                                <div class="search-panel__item search-panel__recommend">
                                    <div class="search-panel__item_title">
                                        <span class="search-panel__icon icon-star"></span>
                                        产品推荐
                                    </div>
                                    <div class="search-panel__item_con">
                                        <div>星火认知大模型</div>
                                        <div>手写文字识别</div>
                                        <div>人脸识别</div>
                                        <div>智能翻译</div>
                                        <div>AI招聘</div>
                                        <div>讯飞飞签</div>
                                        <div>企业数字化</div>
                                        <div>机器人客服</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="affix-panel-content__applet ">
                        <div class="title">体验中心</div>
                        <img src="/static/img/layout/affix/qr-code2.png" alt="">
                        <div class="desc">热门AI技术，扫码体验</div>
                    </div>
                </div>
            </div>
            <div class="feedback-modal ">
                <div class="modal-top">
                    <span>×</span>
                </div>
                <p class="modal-title">意见/投诉反馈</p>
                <p class="modal-mention">本入口仅处理对讯飞开放平台的意见和投诉，如果您是问题咨询请
                    <span class="im-btn">点击此处</span>
                    进行IM咨询，点击IM页面转人工服务可以为您转人工处理。
                </p>
                <div class="modal-content">
                    <div class="modal-ipt-item">
                        <div class="modal-ipt-item-left">
                            <i class="star">*&nbsp;</i>
                            您的称呼</div>
                        <div class="modal-ipt-item-right">
                            <input type="text" class="ipt" value="" maxlength="40" placeholder="请输入您的称呼">
                        </div>
                    </div>
                    <div class="modal-ipt-item">
                        <div class="modal-ipt-item-left">
                            <i class="star">*&nbsp;</i>
                            手机号码
                        </div>
                        <div class="modal-ipt-item-right phone">
                            <select class="ipt select">
                                <option value="+86">(+86)中国</option><option value="+853">(+853)中国澳门 (中國澳門)</option><option value="+886">(+886)中国台湾 (中國台灣)</option><option value="+852">(+852)中国香港</option><option value="+355">(+355)阿尔巴尼亚 (Shqipëri)</option><option value="+213">(+213)阿尔及利亚 (Algeria)</option><option value="+93">(+93)阿富汗 (‫افغانستان‬‎)</option><option value="+54">(+54)阿根廷 (Argentina)</option><option value="+971">(+971)阿拉伯联合酋长国 (‫الإمارات العربية المتحدة‬‎)</option><option value="+297">(+297)阿鲁巴 (Aruba)</option><option value="+968">(+968)阿曼 (‫عُمان‬‎)</option><option value="+994">(+994)阿塞拜疆 (Azərbaycan)</option><option value="+247">(+247)阿森松岛 (Ascension Island)</option><option value="+20">(+20)埃及 (‫مصر‬‎)</option><option value="+251">(+251)埃塞俄比亚 (Ethiopia)</option><option value="+353">(+353)爱尔兰 (Ireland)</option><option value="+372">(+372)爱沙尼亚 (Eesti)</option><option value="+376">(+376)安道尔 (Andorra)</option><option value="+244">(+244)安哥拉 (Angola)</option><option value="+1264">(+1264)安圭拉 (Anguilla)</option><option value="+1268">(+1268)安提瓜和巴布达 (Antigua &amp; Barbuda)</option><option value="+43">(+43)奥地利 (Österreich)</option><option value="+61">(+61)澳大利亚 (Australia)</option><option value="+1246">(+1246)巴巴多斯 (Barbados)</option><option value="+675">(+675)巴布亚新几内亚 (Papua New Guinea)</option><option value="+1242">(+1242)巴哈马 (Bahamas)</option><option value="+92">(+92)巴基斯坦 (‫پاکستان‬‎)</option><option value="+595">(+595)巴拉圭 (Paraguay)</option><option value="+970">(+970)巴勒斯坦 (‫فلسطين‬‎)</option><option value="+973">(+973)巴林 (‫البحرين‬‎)</option><option value="+507">(+507)巴拿马 (Panamá)</option><option value="+55">(+55)巴西 (Brasil)</option><option value="+375">(+375)白俄罗斯 (Беларусь)</option><option value="+1441">(+1441)百慕大 (Bermuda)</option><option value="+359">(+359)保加利亚 (България)</option><option value="+229">(+229)贝宁 (Bénin)</option><option value="+32">(+32)比利时 (Belgium)</option><option value="+354">(+354)冰岛 (Ísland)</option><option value="+591">(+591)玻利维亚 (Bolivia)</option><option value="+1787">(+1787)波多黎各 (Puerto Rico)</option><option value="+48">(+48)波兰 (Polska)</option><option value="+387">(+387)波斯尼亚和黑塞哥维那 (Bosna i Hercegovina)</option><option value="+267">(+267)博茨瓦纳 (Botswana)</option><option value="+501">(+501)伯利兹 (Belize)</option><option value="+975">(+975)不丹 (འབྲུག)</option><option value="+226">(+226)布基纳法索 (Burkina Faso)</option><option value="+257">(+257)布隆迪 (Uburundi)</option><option value="+850">(+850)朝鲜 (북한)</option><option value="+240">(+240)赤道几内亚 (Guinea Ecuatorial)</option><option value="+45">(+45)丹麦 (Danmark)</option><option value="+49">(+49)德国 (Deutschland)</option><option value="+670">(+670)东帝汶 (Timor-Leste)</option><option value="+228">(+228)多哥 (Togo)</option><option value="+1809">(+1809)多米尼加共和国 (República Dominicana)</option><option value="+1767">(+1767)多米尼克 (Dominica)</option><option value="+7">(+7)俄罗斯 (Россия)</option><option value="+593">(+593)厄瓜多尔 (Ecuador)</option><option value="+291">(+291)厄立特里亚 (Eritrea)</option><option value="+33">(+33)法国 (France)</option><option value="+298">(+298)法罗群岛 (Føroyar)</option><option value="+689">(+689)法属波利尼西亚 (Polynésie française)</option><option value="+594">(+594)法属圭亚那 (Guyane française)</option><option value="+63">(+63)菲律宾 (Philippines)</option><option value="+358">(+358)芬兰 (Suomi)</option><option value="+238">(+238)佛得角 (Kabu Verdi)</option><option value="+500">(+500)福克兰群岛（马尔维纳斯群岛） (Falkland Islands (Islas Malvinas))</option><option value="+220">(+220)冈比亚 (Gambia)</option><option value="+242">(+242)刚果（布） (Congo-Brazzaville)</option><option value="+243">(+243)刚果（金） (Jamhuri ya Kidemokrasia ya Kongo)</option><option value="+57">(+57)哥伦比亚 (Colombia)</option><option value="+506">(+506)哥斯达黎加 (Costa Rica)</option><option value="+1473">(+1473)格林纳达 (Grenada)</option><option value="+299">(+299)格陵兰 (Kalaallit Nunaat)</option><option value="+995">(+995)格鲁吉亚 (საქართველო)</option><option value="+590">(+590)瓜德罗普 (Guadeloupe)</option><option value="+592">(+592)圭亚那 (Guyana)</option><option value="+7">(+7)哈萨克斯坦 (Казахстан)</option><option value="+509">(+509)海地 (Haiti)</option><option value="+82">(+82)韩国 (대한민국)</option><option value="+31">(+31)荷兰 (Nederland)</option><option value="+599">(+599)荷属加勒比区 (Caribbean Netherlands)</option><option value="+1721">(+1721)荷属圣马丁 (Sint Maarten)</option><option value="+382">(+382)黑山 (Crna Gora)</option><option value="+504">(+504)洪都拉斯 (Honduras)</option><option value="+686">(+686)基里巴斯 (Kiribati)</option><option value="+253">(+253)吉布提 (Djibouti)</option><option value="+996">(+996)吉尔吉斯斯坦 (Кыргызстан)</option><option value="+224">(+224)几内亚 (Guinée)</option><option value="+245">(+245)几内亚比绍 (Guiné-Bissau)</option><option value="+001">(+001)加拿大 (Canada)</option><option value="+233">(+233)加纳 (Gaana)</option><option value="+241">(+241)加蓬 (Gabon)</option><option value="+855">(+855)柬埔寨 (កម្ពុជា)</option><option value="+420">(+420)捷克 (Česko)</option><option value="+263">(+263)津巴布韦 (Zimbabwe)</option><option value="+237">(+237)喀麦隆 (Cameroun)</option><option value="+974">(+974)卡塔尔 (‫قطر‬‎)</option><option value="+1345">(+1345)开曼群岛 (Cayman Islands)</option><option value="+269">(+269)科摩罗 (‫جزر القمر‬‎)</option><option value="+225">(+225)科特迪瓦 (Côte d’Ivoire)</option><option value="+965">(+965)科威特 (‫الكويت‬‎)</option><option value="+385">(+385)克罗地亚 (Hrvatska)</option><option value="+254">(+254)肯尼亚 (Kenya)</option><option value="+682">(+682)库克群岛 (Cook Islands)</option><option value="+599">(+599)库拉索 (Curaçao)</option><option value="+371">(+371)拉脱维亚 (Latvija)</option><option value="+266">(+266)莱索托 (Lesotho)</option><option value="+856">(+856)老挝 (ລາວ)</option><option value="+961">(+961)黎巴嫩 (‫لبنان‬‎)</option><option value="+231">(+231)利比里亚 (Liberia)</option><option value="+218">(+218)利比亚 (‫ليبيا‬‎)</option><option value="+370">(+370)立陶宛 (Lietuva)</option><option value="+423">(+423)列支敦士登 (Liechtenstein)</option><option value="+262">(+262)留尼汪 (La Réunion)</option><option value="+250">(+250)卢旺达 (Rwanda)</option><option value="+40">(+40)罗马尼亚 (România)</option><option value="+261">(+261)马达加斯加 (Madagasikara)</option><option value="+356">(+356)马耳他 (Malta)</option><option value="+960">(+960)马尔代夫 (Maldives)</option><option value="+265">(+265)马拉维 (Malawi)</option><option value="+60">(+60)马来西亚 (Malaysia)</option><option value="+223">(+223)马里 (Mali)</option><option value="+389">(+389)马其顿（前南斯拉夫马其顿共和国） (Република Македонија)</option><option value="+692">(+692)马绍尔群岛 (Marshall Islands)</option><option value="+596">(+596)马提尼克 (Martinique)</option><option value="+222">(+222)毛里塔尼亚 (‫موريتانيا‬‎)</option><option value="+1">(+1)美国 (United States)</option><option value="+1684">(+1684)美属萨摩亚 (American Samoa)</option><option value="+1340">(+1340)美属维尔京群岛 (U.S. Virgin Islands)</option><option value="+976">(+976)蒙古 (Монгол)</option><option value="+1664">(+1664)蒙特塞拉特 (Montserrat)</option><option value="+880">(+880)孟加拉国 (বাংলাদেশ)</option><option value="+51">(+51)秘鲁 (Perú)</option><option value="+691">(+691)密克罗尼西亚 (Micronesia)</option><option value="+95">(+95)缅甸 (မြန်မာ)</option><option value="+373">(+373)摩尔多瓦 (Republica Moldova)</option><option value="+212">(+212)摩洛哥 (Morocco)</option><option value="+377">(+377)摩纳哥 (Monaco)</option><option value="+258">(+258)莫桑比克 (Moçambique)</option><option value="+52">(+52)墨西哥 (México)</option><option value="+264">(+264)纳米比亚 (Namibië)</option><option value="+27">(+27)南非 (South Africa)</option><option value="+211">(+211)南苏丹 (‫جنوب السودان‬‎)</option><option value="+977">(+977)尼泊尔 (नेपाल)</option><option value="+505">(+505)尼加拉瓜 (Nicaragua)</option><option value="+227">(+227)尼日尔 (Nijar)</option><option value="+234">(+234)尼日利亚 (Nigeria)</option><option value="+47">(+47)挪威 (Norge)</option><option value="+672">(+672)诺福克岛 (Norfolk Island)</option><option value="+680">(+680)帕劳 (Palau)</option><option value="+351">(+351)葡萄牙 (Portugal)</option><option value="+81">(+81)日本</option><option value="+46">(+46)瑞典 (Sverige)</option><option value="+41">(+41)瑞士 (Schweiz)</option><option value="+503">(+503)萨尔瓦多 (El Salvador)</option><option value="+685">(+685)萨摩亚 (Samoa)</option><option value="+381">(+381)塞尔维亚 (Србија)</option><option value="+232">(+232)塞拉利昂 (Sierra Leone)</option><option value="+221">(+221)塞内加尔 (Senegal)</option><option value="+357">(+357)塞浦路斯 (Κύπρος)</option><option value="+248">(+248)塞舌尔 (Seychelles)</option><option value="+966">(+966)沙特阿拉伯 (‫المملكة العربية السعودية‬‎)</option><option value="+239">(+239)圣多美和普林西比 (São Tomé e Príncipe)</option><option value="+290">(+290)圣赫勒拿 (St. Helena)</option><option value="+1869">(+1869)圣基茨和尼维斯 (St. Kitts &amp; Nevis)</option><option value="+1758">(+1758)圣卢西亚 (St. Lucia)</option><option value="+00">(+00)圣马丁岛 (Saint-Martin)</option><option value="+378">(+378)圣马力诺 (San Marino)</option><option value="+508">(+508)圣皮埃尔和密克隆群岛 (Saint-Pierre-et-Miquelon)</option><option value="+1784">(+1784)圣文森特和格林纳丁斯 (St. Vincent &amp; Grenadines)</option><option value="+94">(+94)斯里兰卡 (ශ්‍රී ලංකාව)</option><option value="+421">(+421)斯洛伐克 (Slovensko)</option><option value="+386">(+386)斯洛文尼亚 (Slovenija)</option><option value="+268">(+268)斯威士兰 (Swaziland)</option><option value="+249">(+249)苏丹 (‫السودان‬‎)</option><option value="+597">(+597)苏里南 (Suriname)</option><option value="+252">(+252)索马里 (Soomaaliya)</option><option value="+677">(+677)所罗门群岛 (Solomon Islands)</option><option value="+992">(+992)塔吉克斯坦 (Tajikistan)</option><option value="+66">(+66)泰国 (ไทย)</option><option value="+255">(+255)坦桑尼亚 (Tanzania)</option><option value="+676">(+676)汤加 (Tonga)</option><option value="+1649">(+1649)特克斯和凯科斯群岛 (Turks &amp; Caicos Islands)</option><option value="+1868">(+1868)特立尼达和多巴哥 (Trinidad &amp; Tobago)</option><option value="+216">(+216)突尼斯 (Tunisia)</option><option value="+688">(+688)图瓦卢 (Tuvalu)</option><option value="+90">(+90)土耳其 (Türkiye)</option><option value="+993">(+993)土库曼斯坦 (Turkmenistan)</option><option value="+690">(+690)托克劳 (Tokelau)</option><option value="+681">(+681)瓦利斯和富图纳 (Wallis &amp; Futuna)</option><option value="+502">(+502)危地马拉 (Guatemala)</option><option value="+58">(+58)委内瑞拉 (Venezuela)</option><option value="+673">(+673)文莱 (Brunei)</option><option value="+256">(+256)乌干达 (Uganda)</option><option value="+380">(+380)乌克兰 (Україна)</option><option value="+598">(+598)乌拉圭 (Uruguay)</option><option value="+998">(+998)乌兹别克斯坦 (Oʻzbekiston)</option><option value="+34">(+34)西班牙 (España)</option><option value="+30">(+30)希腊 (Ελλάδα)</option><option value="+65">(+65)新加坡 (Singapore)</option><option value="+687">(+687)新喀里多尼亚 (Nouvelle-Calédonie)</option><option value="+64">(+64)新西兰 (New Zealand)</option><option value="+36">(+36)匈牙利 (Magyarország)</option><option value="+963">(+963)叙利亚 (‫سوريا‬‎)</option><option value="+1876">(+1876)牙买加 (Jamaica)</option><option value="+374">(+374)亚美尼亚 (Հայաստան)</option><option value="+967">(+967)也门 (‫اليمن‬‎)</option><option value="+964">(+964)伊拉克 (‫العراق‬‎)</option><option value="+98">(+98)伊朗 (‫ایران‬‎)</option><option value="+972">(+972)以色列 (‫ישראל‬‎)</option><option value="+39">(+39)意大利 (Italia)</option><option value="+91">(+91)印度 (भारत)</option><option value="+62">(+62)印度尼西亚 (Indonesia)</option><option value="+44">(+44)英国 (United Kingdom)</option><option value="+1284">(+1284)英属维尔京群岛 (British Virgin Islands)</option><option value="+246">(+246)英属印度洋领地 (British Indian Ocean Territory)</option><option value="+962">(+962)约旦 (‫الأردن‬‎)</option><option value="+84">(+84)越南 (Việt Nam)</option><option value="+260">(+260)赞比亚 (Zambia)</option><option value="+235">(+235)乍得 (Tchad)</option><option value="+350">(+350)直布罗陀 (Gibraltar)</option><option value="+56">(+56)智利 (Chile)</option><option value="+236">(+236)中非共和国 (République centrafricaine)</option><option value="+674">(+674)瑙鲁 (Nauru)</option><option value="+379">(+379)梵蒂冈 (Città del Vaticano)</option><option value="+679">(+679)斐济 (Fiji)</option></select><input type="text" class="ipt" value="" placeholder="请输入您的手机号码"></div></div><div class="modal-ipt-item"><div class="modal-ipt-item-left"><i class="star">*&nbsp;</i>意见投诉</div><div class="modal-ipt-item-right"><textarea class="ipt textarea" type="text" maxlength="128" placeholder="请详细填写，以便我们更加详细地了解您的诉求。(不多于128字)"></textarea></div></div></div><button type="button" class="gaia-btn gaia-btn-primary" disabled="">立即提交</button></div></div>

        <!-- 拍照 -->
        <div class="camera-card">
            <div>
                <!-- 摄像头调用 -->
                <video ref="videoCamera" :width="videoWidth" :height="videoHeight" autoplay></video>

                <!-- 用绘制canvas-转换给img -->
                <canvas style="display: none" ref="canvasCamera" :width="videoWidth" :height="videoHeight"></canvas>
            </div>
        </div>

        <!-- 3个按钮 -->
        <div class="card-child">
            <el-button @click="getCompetence()" type="primary" class="btn_photo">开启</el-button>
            <el-button @click="stopNavigator()" type="warning" class="btn_photo">关闭</el-button>
            <el-button @click="setImage()" type="success" class="btn_photo">拍照</el-button>
        </div>


        <!-- 拍照结果 -->
        <div v-if="imgSrc" class="img_res_camera">
            <div class="card-title">
                拍摄预览
            </div>
            <img :src="imgSrc" alt class="res_img" />
        </div>

        <!-- 上传按钮 -->
        <div class="card-child">
            <el-button v-if="imgSrc" type="danger" class="btn_photo">取消</el-button>
            <el-button v-if="imgSrc" type="warning" class="btn_photo">分享</el-button>
            <el-button v-if="imgSrc" type="success" @click="sendPhotoToServer(this.imgSrc)" class="btn_photo">上传</el-button>
        </div>
    </div>
</template>

<script lang="ts">
import { defineComponent, ref, onMounted } from 'vue';
import axios from 'axios'; // 导入axios

export default defineComponent({
    data() {
        return {
            videoWidth: 300,
            videoHeight: 200,
            imgSrc: "",
            thisCanvas: null as HTMLCanvasElement | null,
            thisContext: null as CanvasRenderingContext2D | null,
            thisVideo: null as HTMLVideoElement | null,
            openVideo: false,
            info: "",
        };
    },
    mounted() {
        this.getCompetence(); // 进入页面就调用摄像头

        this.updateVideoSize(); // 初始化时调用一次

        // 监听窗口大小变化，动态更新 videoWidth 和 videoHeight
        window.addEventListener('resize', this.updateVideoSize);
    },
    beforeUnmount() {
        window.removeEventListener('resize', this.updateVideoSize);
    },
    methods: {
        // 更新video大小
        updateVideoSize() {
            const screenWidth = window.innerWidth;
            if (screenWidth < 600) {
                this.videoWidth = 200;
                this.videoHeight = 130;
                return
            }
            this.videoWidth = 300;
            this.videoHeight = 200;
            console.log(this.videoWidth)
        },

        // 上传照片
        sendPhotoToServer(photoData: string) {
            const url = 'http://127.0.0.1:5500/photo'; // 后端接口地址
            console.log(photoData);

            // 构造请求参数
            const params = new FormData();
            params.append('photo', photoData);

            // 使用 axios 发送 POST 请求
            axios.post(url, params)
                .then(response => {
                    console.log('照片上传成功:', response);
                    this.info = response
                    // 处理后端返回的响应
                    this.$emit('message', response.data); // 触发自定义事件并传递信息
                })
                .catch(error => {
                    // 处理后端返回的响应
                    this.$emit('message', response.data); // 触发自定义事件并传递信息
                    console.error('照片上传失败', error);

                });
        },

        // 调用权限（打开摄像头功能）
        getCompetence() {
            const thisCanvas = this.$refs.canvasCamera as HTMLCanvasElement;
            const thisContext = thisCanvas.getContext("2d");
            const thisVideo = this.$refs.videoCamera as HTMLVideoElement;
            this.thisCanvas = thisCanvas;
            this.thisContext = thisContext;
            this.thisVideo = thisVideo;
            this.thisVideo.style.display = "block";
            // 获取媒体属性，旧版本浏览器可能不支持 mediaDevices，我们首先设置一个空对象
            if (navigator.mediaDevices === undefined) {
                navigator.mediaDevices = {} as any;
            }
            // 一些浏览器实现了部分 mediaDevices，我们不能只分配一个对象
            // 使用 getUserMedia，因为它会覆盖现有的属性。
            // 这里，如果缺少 getUserMedia 属性，就添加它。
            if (navigator.mediaDevices.getUserMedia === undefined) {
                navigator.mediaDevices.getUserMedia = function (
                    constraints: MediaStreamConstraints
                ) {
                    // 首先获取现存的 getUserMedia（如果存在）
                    const getUserMedia =
                        navigator.webkitGetUserMedia ||
                        navigator.mozGetUserMedia ||
                        navigator.getUserMedia;
                    // 有些浏览器不支持，会返回错误信息
                    // 保持接口一致
                    if (!getUserMedia) {
                        // 不存在则报错
                        return Promise.reject(
                            new Error("getUserMedia is not implemented in this browser")
                        );
                    }
                    // 否则，使用 Promise 将调用包装到旧的 navigator.getUserMedia
                    return new Promise(function (resolve, reject) {
                        getUserMedia.call(navigator, constraints, resolve, reject);
                    });
                };
            }
            const constraints: MediaStreamConstraints = {
                audio: false,
                video: {
                    width: this.videoWidth,
                    height: this.videoHeight,
                    // transform: "scaleX(-1)",
                },
            };
            navigator.mediaDevices
                .getUserMedia(constraints)
                .then((stream) => {
                    // 旧的浏览器可能没有 srcObject
                    if ("srcObject" in this.thisVideo!) {
                        (this.thisVideo! as any).srcObject = stream;
                    } else {
                        // 避免在新的浏览器中使用它，因为它正在被弃用。
                        (this.thisVideo! as any).src = window.URL.createObjectURL(stream);
                    }
                    this.thisVideo!.onloadedmetadata = function (e) {
                        (this! as any).play();
                    };
                })
                .catch((err) => {
                    console.log(err);
                });
        },
        // 绘制图片（拍照功能）
        setImage() {
            // canvas 画图
            this.thisContext!.drawImage(
                this.thisVideo!,
                0,
                0,
                this.videoWidth,
                this.videoHeight
            );
            // 获取图片 base64 链接
            const image = this.thisCanvas!.toDataURL("image/png");
            this.imgSrc = image; // 赋值并预览图片
        },
        // 关闭摄像头
        stopNavigator() {
            (this.thisVideo!.srcObject as MediaStream).getTracks()[0].stop();
        },
    },
});
</script>



<style scoped>
.content {
    display: flex;
    flex-direction: column;
    width: 80%;
    /* height: 80vh; */
    margin: auto;
    margin-top: 10vh;
    margin-bottom: 10vh;
    user-select: none;
}


.card-child {
    padding: 0.5rem;
}


@media (max-width: 600px) {
    .btn_photo {
        width: 3rem;
        /* font: 1rem sans-serif; */
        font-size: 1vw;
    }
}

.camera-card {
    display: flex;
    justify-content: flex-start;
    align-items: center;
}

.card-title {
    font-size: 1.3vw;
    font-weight: bold;
}
</style>



