package com.yakasoftware.zerotech.views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.yakasoftware.zerotech.Lines.SimpleLine

@Composable
fun AgreementScreen(navController: NavHostController) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.primary) {
        val fontSizeMainTitle = 24.dp
        val fontSizeSubTitle = 18.dp
        val fontSizeText = 14.dp
        Column(modifier = Modifier.fillMaxSize()) {
            Spacer(modifier = Modifier.padding(top = 32.dp))
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(text = "Kullanıcı Sözleşmesi", fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = with(LocalDensity.current) { fontSizeMainTitle.toSp() }
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 4.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Row(Modifier.fillMaxWidth(0.8f)) {
                            SimpleLine()
                        }
                    }
                    Spacer(modifier = Modifier.padding(top = 18.dp))
                    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                        Spacer(modifier = Modifier.padding(start = 8.dp))
                        Text(text = "Bu mobil uygulamayı kullanan ve hizmetlerimizden yararlanan kullanıcılar aşağıdaki şartları kabul etmiş sayılırlar.",
                            color = MaterialTheme.colorScheme.tertiary,
                            fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 32.dp))
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(start = 12.dp)
                    ) {
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.Sorumluluklar", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 18.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.1.Firma, uygulamadaki fiyatlar ve sunulan ürün ve hizmetler üzerinde değişiklik yapma hakkını her zaman saklı tutar.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.2.Firma, üyenin uygulama hizmetlerinden, teknik arızalar dışında yararlandırılacağını kabul ve taahhüt eder.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.3.Kullanıcı, uygulamanın kullanımı sırasında tersine mühendislik yapmayacağını ya da uygulamanın kaynak kodunu bulmak veya elde etmek amacına yönelik herhangi bir başka işlemde bulunmayacağını kabul eder. Aksi halde 3. kişilere doğacak zararlardan sorumlu olacağını ve hukuki ve cezai işlemle karşı karşıya kalabileceğini kabul eder.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.4.Kullanıcı, uygulama için verdiği eksik veya yanlış bilgiler nedeniyle uğrayabileceği zararlardan sadece kendisinin sorumlu olduğunu kabul eder. Yanlış bilgi verilmesi durumunda ve bu sözleşmenin kullanıcı tarafından ihlal edilmesi halinde, Firma'nın üyeliği tek taraflı olarak sonlandırma hakkını saklı tuttuğunu kabul eder.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))

                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.5.Firma, uygulamanın iyileştirilmesi ve geliştirilmesi amacıyla, ayrıca yasal gereklilikler çerçevesinde, kullanıcıların uygulamaya erişim sağladıkları internet servis sağlayıcısının adı ve Internet Protokol (IP) adresi, uygulamaya erişilen tarih ve saat, uygulamada gezinme sırasında erişilen sayfalar ve uygulamaya doğrudan bağlanma sağlayan web sitesinin Internet adresi gibi bilgileri toplayabilir. Kullanıcı, bu bilgilerin toplanmasını kabul eder.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.6.Kullanıcı, uygulama içinde veya iletişimlerinde genel ahlaka ve adaba aykırı, kanuna aykırı, 3. kişilerin haklarını ihlal eden, yanıltıcı, saldırgan, müstehcen, pornografik, kişilik haklarını ihlal eden, telif haklarına aykırı, yasa dışı faaliyetleri teşvik eden içerikler oluşturmayacağını veya paylaşmayacağını kabul eder. Aksi takdirde oluşacak zarardan tamamen kendisi sorumludur ve Firma, bu tür hesapları askıya alma, sonlandırma veya yasal süreç başlatma hakkını saklı tutar. Bu nedenle yargı mercilerinden etkinlik veya kullanıcı hesapları ile ilgili bilgi talepleri geldiğinde, Firma, bu bilgileri paylaşma hakkını saklı tutar.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.7.Uygulamanın kullanıcıları arasındaki ilişkiler, kullanıcıların kendi sorumluluğundadır.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "2.Fikri Mülkiyet Hakları", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "2.1. Bu uygulamada yer alan ünvan, işletme adı, marka, patent, logo, tasarım, bilgi ve yöntem gibi tescilli veya tescilsiz tüm fikri mülkiyet hakları Firma'ya veya ilgili hak sahiplerine aittir ve ulusal ve uluslararası hukuk tarafından korunmaktadır. Bu uygulamanın ziyaret edilmesi veya uygulamadaki hizmetlerden yararlanılması, hiçbir hak vermez.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "2.2.Uygulamada yer alan bilgiler hiçbir şekilde çoğaltılamaz, yayınlanamaz, kopyalanamaz, sunulamaz ve/veya aktarılamaz. Uygulamanın bütünü veya bir kısmı diğer bir mobil uygulamada izinsiz olarak kullanılamaz. Böyle bir ihlal durumunda, kullanıcı, üçüncü kişilerin uğradıkları zararlardan dolayı Firma'dan talep edilen tazminat miktarını ve mahkeme masrafları ve avukatlık ücreti de dahil ancak bununla sınırlı olmamak üzere diğer her türlü yükümlülükleri karşılamakla sorumlu olacaktır.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "3.Gizli Bilgi", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "3.1.Firma, uygulama üzerinden kullanıcıların ilettiği kişisel bilgileri 3. kişilere açıklamayacaktır. Bu kişisel bilgiler; kişi adı-soyadı, adresi, telefon numarası, cep telefonu, e-posta adresi gibi Kullanıcı'yı tanımlamaya yönelik her türlü diğer bilgiyi içermekte olup, kısaca 'Gizli Bilgiler' olarak anılacaktır.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "3.2. Kullanıcı, tanıtım, reklam, kampanya, promosyon, duyuru vb. pazarlama faaliyetleri kapsamında kullanılması ile sınırlı olmak üzere, Firma'nın kendisine ait iletişim, portföy durumu ve demografik bilgilerini iştirakleri ya da bağlı bulunduğu grup şirketleri ile paylaşmasına, kendisi veya iştiraklerine yönelik bu bağlamda elektronik ileti almaya onay verdiğini kabul ve beyan eder. Bu kişisel bilgiler Firma bünyesinde müşteri profili belirlemek, müşteri profiline uygun promosyon ve kampanyalar sunmak ve istatistiksel çalışmalar yapmak amacıyla kullanılabilecektir.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "3.3.Kullanıcı, işbu sözleşme ile verdiği onayı, hiçbir gerekçe açıklamaksızın iptal etme hakkına sahiptir. İptal işlemini Firma, derhal işleme alıp, 3 (üç) iş günü içerisinde kullanıcıyı elektronik ileti almaktan imtina eder.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "3.4.Gizli Bilgiler, ancak resmi makamlarca usulü dairesinde bu bilgilerin talep edilmesi halinde ve yürürlükteki emredici mevzuat hükümleri gereğince resmi makamlara açıklama yapılmasının zorunlu olduğu durumlarda resmi makamlara açıklanabilecektir.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "4.Garanti Vermeme", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "4.1.İŞBU SÖZLEŞME MADDESİ UYGULANABİLİR KANUNUN İZİN VERDİĞİ AZAMİ ÖLÇÜDE GEÇERLİ OLACAKTIR. FİRMA TARAFINDAN SUNULAN HİZMETLER \"OLDUĞU GİBİ” VE \"MÜMKÜN OLDUĞU” TEMELDE SUNULMAKTA VE PAZARLANABİLİRLİK, BELİRLİ BİR AMACA UYGUNLUK VEYA İHLAL ETMEME KONUSUNDA TÜM ZIMNİ GARANTİLER DE DÂHİL OLMAK ÜZERE HİZMETLER VEYA UYGULAMA İLE İLGİLİ OLARAK (BUNLARDA YER ALAN TÜM BİLGİLER DÂHİL) SARİH VEYA ZIMNİ, KANUNİ VEYA BAŞKA BİR NİTELİKTE HİÇBİR GARANTİDE BULUNMAMAKTADIR.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "5.Kayıt ve Güvenlik", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "5.1.Kullanıcı, doğru, eksiksiz ve güncel kayıt bilgilerini vermek zorundadır. Aksi halde bu Sözleşme ihlal edilmiş sayılacak ve Kullanıcı bilgilendirilmeksizin hesap kapatılabilecektir.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "5.2.Kullanıcı, uygulama ve üçüncü taraf uygulamalarındaki şifre ve hesap güvenliğinden kendisi sorumludur. Aksi halde oluşacak veri kayıplarından, güvenlik ihlallerinden veya donanım ve cihazların zarar görmesinden Firma sorumlu tutulamaz.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "6.Mücbir Sebep", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "6.1.Tarafların kontrolünde olmayan; tabii afetler, yangın, patlamalar, iç savaşlar, savaşlar, ayaklanmalar, halk hareketleri, seferberlik ilanı, grev, lokavt ve salgın hastalıklar, altyapı ve internet arızaları, elektrik kesintisi gibi sebeplerden (aşağıda birlikte \"Mücbir Sebep” olarak anılacaktır.) dolayı sözleşmeden doğan yükümlülükler taraflarca ifa edilemez hale gelirse, taraflar bundan sorumlu değildir. Bu sürede Taraflar’ın işbu Sözleşme’den doğan hak ve yükümlülükleri askıya alınır.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "7.Sözleşmenin Bütünlüğü ve Uygulanabilirlik", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "7.1.İşbu sözleşme şartlarından biri, kısmen veya tamamen geçersiz hale gelirse, sözleşmenin geri kalanı geçerliliğini korumaya devam eder.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "8.Sözleşmede Yapılacak Değişiklikler", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "8.1.Firma, dilediği zaman uygulamada sunulan hizmetleri ve işbu sözleşme şartlarını kısmen veya tamamen değiştirebilir. Değişiklikler uygulamada yayınlandığı tarihten itibaren geçerli olacaktır. Değişiklikleri takip etmek Kullanıcı’nın sorumluluğundadır. Kullanıcı, sunulan hizmetlerden yararlanmaya devam etmekle bu değişiklikleri de kabul etmiş sayılır.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "9.Tebligat", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "9.1.İşbu Sözleşme ile ilgili taraflara gönderilecek olan tüm bildirimler, Firma'nın bilinen e-posta adresi ve kullanıcının üyelik formunda belirttiği e-posta adresi vasıtasıyla yapılacaktır. Kullanıcı, üye olurken belirttiği adresin geçerli tebligat adresi olduğunu, değişmesi durumunda 5 gün içinde yazılı olarak diğer tarafa bildireceğini, aksi halde bu adrese yapılacak tebligatların geçerli sayılacağını kabul eder.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "10.Delil Sözleşmesi", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "10.1.Taraflar arasında işbu sözleşme ile ilgili işlemler için çıkabilecek her türlü uyuşmazlıklarda Taraflar’ın defter, kayıt ve belgeleri ile ve bilgisayar kayıtları ve faks kayıtları 6100 sayılı Hukuk Muhakemeleri Kanunu uyarınca delil olarak kabul edilecek olup, kullanıcı bu kayıtlara itiraz etmeyeceğini kabul eder.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "11.Uyuşmazlıkların Çözümü", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "11.1.İşbu Sözleşme'nin uygulanmasından veya yorumlanmasından doğacak her türlü uyuşmazlığın çözümünde İstanbul (Merkez) Adliyesi Mahkemeleri ve İcra Daireleri yetkilidir.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 32.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(text = "Gizlilik Sözleşmesi", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeMainTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Row(Modifier.fillMaxWidth(0.8f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 18.dp))
                        Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.Center) {
                            Spacer(modifier = Modifier.padding(start = 8.dp))
                            Text(text = "Zerotech (\"Şirket”) olarak kişisel verilerinizin korunmasına büyük önem veriyoruz. Bu kapsamda 6698 sayılı Kişisel Verilerin Korunması Kanunu (\"KVKK”) uyarınca \"veri sorumlusu” sıfatıyla kişisel verileriniz ve işleme süreçleri hakkında sizleri bilgilendirmek isteriz.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.İşlenen Kişisel Verileriniz Nelerdir", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kimlik bilgileriniz (Ad Soyad, Doğum tarihi, TC Kimlik No)",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "İletişim bilgileriniz (Adres, e-posta adresi, telefon numarası))",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Mobil uygulamayı kullanımınıza ilişkin detaylar (Uygulamadaki davranışlarınız, işlemleriniz, tercihleriniz, kullanıcı hesabınız)",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Size özel tahsis edilmiş Kullanıcı Adı ve Şifreniz",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "2.Kişisel Verilerinizin İşlenme Amaçları", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kişisel verileriniz, kişisel verilerin korunmasına ilişkin mevzuata uygun olarak aşağıdaki amaçlar ile işlenebilecektir:",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kullanıcı hesabınızı yönetmek ve mobil uygulama hizmetlerini sunmak amacıyla kimlik ve iletişim bilgilerinizi,",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Mevzuattan kaynaklanan yükümlülüklerimizi yerine getirmek, yetkili kamu kurum ve kuruluşları ile diğer hukuki yükümlüklerimizi yerine getirmek amaçlarıyla kimlik, iletişim bilgilerinizi,",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kullanıcı hesabınıza ilişkin işlemlerde her türlü dava, cevap ve itiraz hakkının kullanılması amacıyla kimlik, iletişim bilgilerinizi,",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kullanıcı hesabınızı diğer kullanıcı hesaplarından ayırt edilebilmesi ve gerekirse satın alınan ürünlerin veya hizmetlerin düzeltilmesi ve hizmet sonrası operasyonel süreçlerin yönetilmesi amacıyla kimlik ve iletişim bilgilerinizi.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "3.Elektronik Ticari İletişim İzni Vermeniz Halinde:", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "1.Kullanıcı hesabınıza özel kampanyaların, avantajların, promosyonların, reklamların, bilgilendirmelerin, pazarlama faaliyetlerinin oluşturulabilmesi/yapılabilmesi, size yönelik her türlü ticari iletişim faaliyetlerinde bulunulabilmesi amacıyla kimlik, iletişim ve kullanıcı bilgilerinizi işliyoruz.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "4.Kişisel Verilerinizin Toplama Yöntemi ve Hukuki Sebebi:", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kişisel verileriniz Zerotech tarafından; mobil uygulamayı kullanımınız sırasında işlemleriniz, tercihleriniz ve kullanıcı hesabınız ile ilgili işlemler esnasında elektronik sistemler vasıtasıyla toplanmakta ve işlenmektedir.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Zerotech olarak kişisel verilerinizi; mobil uygulamayı kullanımınıza ilişkin olarak \"sözleşmenin kurulması ve ifası için veri işlemenin gerekli olması”, \"hukuki yükümlülüğün yerine getirilmesi” ile \"veri sorumlusunun meşru menfaatleri için veri işlenmesinin zorunlu olması” hukuki sebeplerine dayanarak topluyoruz.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Açık rızanın bulunması; Şirketimizin tabi olduğu sair mevzuatta açıkça öngörülmüş olması,\n" +
                                    "Bir sözleşmenin kurulması veya ifasıyla doğrudan doğruya ilgili olması kaydıyla, sözleşmenin taraflarına ait kişisel verilerin işlenmesinin gerekli olması, talep edilen ürün ve hizmetleri sunabilmek ve akdettiğiniz sözleşmelerinin gereğinin yerine getirilmesi,\n" +
                                    "Hukuki yükümlülüğün yerine getirebilmesi için zorunlu olması,\n" +
                                    "İlgili kişinin kendisi tarafından alenileştirilmiş olması,\n" +
                                    "Bir hakkın tesisi, kullanılması veya korunması için veri işlemenin zorunlu olması,\n" +
                                    "İlgili kişinin temel hak ve özgürlüklerine zarar vermemek kaydıyla, veri sorumlusunun meşru menfaatleri için veri işlenmesinin zorunlu olması.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "4.Kişisel Verilerinizin Aktrarılması:", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Zerotech olarak kişisel verilerinizi:",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Mobil uygulamayı yönetmek, pazarlama faaliyetlerini yürütmek, kişisel veri toplama süreçlerinde hizmet almak gibi amaçlarla yurtiçi ve yurtdışında bulunan iş ortakları ve hizmet sağlayıcılarıyla (pazarlama danışmanları, veri tabanı hizmet sağlayıcıları, elektronik ileti aracı hizmet sağlayıcıları vb.) veya yurtdışındaki iş ortaklarımızın sunucularının yurtdışında olması halinde yurtdışı ile paylaşmaktayız.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "5.Haklarınız", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kanun’un 11. maddesi gereğince; bu Politikanın \"İletişim” bölümünde yer alan yöntemlerle:",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kişisel Verilerinizin işlenip işlenmediğini öğrenme,",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kişisel Verileriniz işlenmişse buna ilişkin bilgi talep etme,",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kişisel Verilerin işlenme amacını ve bunların amacına uygun kullanılıp kullanılmadığını öğrenme,",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Yurtiçinde veya yurtdışında Kişisel Verilerinizin aktarıldığı üçüncü kişileri bilme,",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kişisel Verilerinizin eksik veya yanlış işlenmiş olması halinde bunların düzeltilmesini isteme,\n",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "KVKK mevzuatında öngörülen şartlar çerçevesinde Kişisel Verilerinizin silinmesini veya yok edilmesini isteme,",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "İşlenen verilerin münhasıran otomatik sistemler vasıtasıyla analiz edilmesi suretiyle aleyhinize bir sonucun ortaya çıkmasına itiraz etme,",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Kişisel Verilerin kanuna aykırı olarak işlenmesi sebebiyle zarara uğramanız halinde bu zararın giderilmesini talep etme haklarına sahipsiniz.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 24.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "6.İletişim", fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeSubTitle.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 4.dp))
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Row(Modifier.fillMaxWidth(0.6f)) {
                                SimpleLine()
                            }
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Başvurularınızı Veri Sorumlusu olarak Zerotech'un belirttiği e-posta adresine veya yazılı olarak iletebilirsiniz. İlgili talebin yasalar gereği belirli bir prosedürde yapılması gereken hallerde söz konusu prosedüre uyulması gerektiğini hatırlatmak isteriz.",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Adres: Süleymaniye, Ord. Prof.Dr.Cemil Birsel Cd 34116 Hisarın yanı Nezih Bey İs Hanı Girişin Üstü NO 1417 Fatih Eminönü İstanbul",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Telefon: 05317878856",
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }
                        Spacer(modifier = Modifier.padding(top = 12.dp))
                        Row(modifier = Modifier.fillMaxWidth()) {
                            Text(text = "Eposta: Yusuf.47doksal@icloud.com"
                                    ,
                                color = MaterialTheme.colorScheme.tertiary,
                                fontSize = with(LocalDensity.current) { fontSizeText.toSp() }
                            )
                        }


                    }
                }
                item { 
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }

        }
    }
}