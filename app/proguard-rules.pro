# Proje özel ProGuard kurallarını buraya ekleyin.
# proguardFiles ayarı kullanılarak uygulanan yapılandırma dosyalarını kontrol edebilirsiniz.
#
# Daha fazla ayrıntı için şu adrese bakın:
#   http://developer.android.com/guide/developing/tools/proguard.html

# Eğer projeniz WebView ve JS kullanıyorsa, aşağıdakileri açın ve JavaScript arayüzü için tam nitelikli sınıf adını belirtin.
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Hata ayıklama yığın izlerini hata ayıklama için satır numarası bilgisini korumak için açın.
#-keepattributes SourceFile,LineNumberTable

# Satır numarası bilgisini koruyorsanız, orijinal kaynak dosya adını gizlemek için aşağıdakileri açın.
#-renamesourcefileattribute SourceFile
