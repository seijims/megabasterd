package com.tonikelope.megabasterd;

import com.fasterxml.jackson.databind.ObjectMapper;
import static com.tonikelope.megabasterd.MiscTools.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.nio.ByteBuffer;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.security.spec.RSAPrivateKeySpec;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.GZIPInputStream;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JOptionPane;

/**
 *
 * @author tonikelope
 */
public final class CryptTools {

    public static final int[] AES_ZERO_IV_I32A = {0, 0, 0, 0};

    public static final byte[] AES_ZERO_IV = i32a2bin(AES_ZERO_IV_I32A);

    public static final int PBKDF2_SALT_BYTE_LENGTH = 16;

    public static final int PBKDF2_ITERATIONS = 0x10000;

    public static Cipher genDecrypter(String algo, String mode, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec skeySpec = new SecretKeySpec(key, algo);

        Cipher decryptor = Cipher.getInstance(mode);

        if (iv != null) {

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            decryptor.init(Cipher.DECRYPT_MODE, skeySpec, ivParameterSpec);

        } else {

            decryptor.init(Cipher.DECRYPT_MODE, skeySpec);
        }

        return decryptor;
    }

    public static Cipher genCrypter(String algo, String mode, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException {
        SecretKeySpec skeySpec = new SecretKeySpec(key, algo);

        Cipher cryptor = Cipher.getInstance(mode);

        if (iv != null) {

            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);

            cryptor.init(Cipher.ENCRYPT_MODE, skeySpec, ivParameterSpec);

        } else {

            cryptor.init(Cipher.ENCRYPT_MODE, skeySpec);
        }

        return cryptor;
    }

    public static byte[] aes_cbc_encrypt(byte[] data, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher cryptor = CryptTools.genCrypter("AES", "AES/CBC/NoPadding", key, iv);

        return cryptor.doFinal(data);
    }

    public static byte[] aes_cbc_encrypt_pkcs7(byte[] data, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher cryptor = CryptTools.genCrypter("AES", "AES/CBC/PKCS5Padding", key, iv);

        return cryptor.doFinal(data);
    }

    public static byte[] aes_cbc_decrypt(byte[] data, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher decryptor = CryptTools.genDecrypter("AES", "AES/CBC/NoPadding", key, iv);

        return decryptor.doFinal(data);
    }

    public static byte[] aes_cbc_decrypt_pkcs7(byte[] data, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher decryptor = CryptTools.genDecrypter("AES", "AES/CBC/PKCS5Padding", key, iv);

        return decryptor.doFinal(data);
    }

    public static byte[] aes_ecb_encrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher cryptor = CryptTools.genCrypter("AES", "AES/ECB/NoPadding", key, null);

        return cryptor.doFinal(data);
    }

    public static byte[] aes_ecb_decrypt(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher decryptor = CryptTools.genDecrypter("AES", "AES/ECB/NoPadding", key, null);

        return decryptor.doFinal(data);
    }

    public static byte[] aes_ecb_encrypt_pkcs7(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher cryptor = CryptTools.genCrypter("AES", "AES/ECB/PKCS5Padding", key, null);

        return cryptor.doFinal(data);
    }

    public static byte[] aes_ecb_decrypt_pkcs7(byte[] data, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher decryptor = CryptTools.genDecrypter("AES", "AES/ECB/PKCS5Padding", key, null);

        return decryptor.doFinal(data);
    }

    public static byte[] aes_ctr_encrypt(byte[] data, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher cryptor = CryptTools.genCrypter("AES", "AES/CTR/NoPadding", key, iv);

        return cryptor.doFinal(data);
    }

    public static byte[] aes_ctr_decrypt(byte[] data, byte[] key, byte[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher decryptor = CryptTools.genDecrypter("AES", "AES/CTR/NoPadding", key, iv);

        return decryptor.doFinal(data);
    }

    public static int[] aes_cbc_encrypt_ia32(int[] data, int[] key, int[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher cryptor = CryptTools.genCrypter("AES", "AES/CBC/NoPadding", i32a2bin(key), i32a2bin(iv));

        return bin2i32a(cryptor.doFinal(i32a2bin(data)));
    }

    public static int[] aes_cbc_decrypt_ia32(int[] data, int[] key, int[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher decryptor = CryptTools.genDecrypter("AES", "AES/CBC/NoPadding", i32a2bin(key), i32a2bin(iv));

        return bin2i32a(decryptor.doFinal(i32a2bin(data)));
    }

    public static int[] aes_ecb_encrypt_ia32(int[] data, int[] key, int[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher cryptor = CryptTools.genCrypter("AES", "AES/ECB/NoPadding", i32a2bin(key), i32a2bin(iv));

        return bin2i32a(cryptor.doFinal(i32a2bin(data)));
    }

    public static int[] aes_ecb_decrypt_ia32(int[] data, int[] key, int[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher decryptor = CryptTools.genDecrypter("AES", "AES/ECB/NoPadding", i32a2bin(key), i32a2bin(iv));

        return bin2i32a(decryptor.doFinal(i32a2bin(data)));
    }

    public static int[] aes_ctr_encrypt_ia32(int[] data, int[] key, int[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher cryptor = CryptTools.genCrypter("AES", "AES/CTR/NoPadding", i32a2bin(key), i32a2bin(iv));

        return bin2i32a(cryptor.doFinal(i32a2bin(data)));
    }

    public static int[] aes_ctr_decrypt_ia32(int[] data, int[] key, int[] iv) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        Cipher decryptor = CryptTools.genDecrypter("AES", "AES/CTR/NoPadding", i32a2bin(key), i32a2bin(iv));

        return bin2i32a(decryptor.doFinal(i32a2bin(data)));
    }

    public static byte[] rsaDecrypt(BigInteger enc_data, BigInteger p, BigInteger q, BigInteger d) throws NoSuchAlgorithmException, InvalidKeySpecException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException {

        RSAPrivateKeySpec privateSpec = new RSAPrivateKeySpec(p.multiply(q), d);

        KeyFactory factory = KeyFactory.getInstance("RSA");

        Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");

        RSAPrivateKey privKey = (RSAPrivateKey) factory.generatePrivate(privateSpec);

        cipher.init(Cipher.DECRYPT_MODE, privKey);

        byte[] enc_data_byte = enc_data.toByteArray();

        if (enc_data_byte[0] == 0) {

            enc_data_byte = Arrays.copyOfRange(enc_data_byte, 1, enc_data_byte.length);
        }

        byte[] plainText = cipher.doFinal(enc_data_byte);

        if (plainText[0] == 0) {

            plainText = Arrays.copyOfRange(plainText, 1, plainText.length);
        }

        return plainText;
    }

    public static byte[] initMEGALinkKey(String key_string) {
        int[] int_key = bin2i32a(UrlBASE642Bin(key_string));
        int[] k = new int[4];

        k[0] = int_key[0] ^ int_key[4];
        k[1] = int_key[1] ^ int_key[5];
        k[2] = int_key[2] ^ int_key[6];
        k[3] = int_key[3] ^ int_key[7];

        return i32a2bin(k);
    }

    public static byte[] initMEGALinkKeyIV(String key_string) throws Exception {
        int[] int_key = bin2i32a(UrlBASE642Bin(key_string));
        int[] iv = new int[4];

        iv[0] = int_key[4];
        iv[1] = int_key[5];
        iv[2] = 0;
        iv[3] = 0;

        return i32a2bin(iv);
    }

    public static byte[] forwardMEGALinkKeyIV(byte[] iv, long forward_bytes) {
        byte[] new_iv = new byte[iv.length];

        System.arraycopy(iv, 0, new_iv, 0, iv.length / 2);

        byte[] ctr = long2bytearray(forward_bytes / iv.length);

        System.arraycopy(ctr, 0, new_iv, iv.length / 2, ctr.length);

        return new_iv;
    }

    public static String decryptMegaDownloaderLink(String link) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, Exception, IllegalBlockSizeException, BadPaddingException {
        String[] keys = {"6B316F36416C2D316B7A3F217A30357958585858585858585858585858585858", "ED1F4C200B35139806B260563B3D3876F011B4750F3A1A4A5EFD0BBE67554B44"};
        String iv = "79F10A01844A0B27FF5B2D4E0ED3163E";

        String enc_type, folder, dec_link;

        if ((enc_type = findFirstRegex("mega://f?(enc[0-9]*)\\?", link, 1)) != null) {
            Cipher decrypter;

            String the_key = null;

            switch (enc_type.toLowerCase()) {
                case "enc":
                    the_key = keys[0];
                    break;
                case "enc2":
                    the_key = keys[1];
                    break;
            }

            folder = findFirstRegex("mega://(f)?enc[0-9]*\\?", link, 1);

            decrypter = CryptTools.genDecrypter("AES", "AES/CBC/NoPadding", hex2bin(the_key), hex2bin(iv));

            byte[] decrypted_data = decrypter.doFinal(UrlBASE642Bin(findFirstRegex("mega://f?enc[0-9]*\\?([\\da-zA-Z_,-]*)", link, 1)));

            dec_link = new String(decrypted_data).trim();

            return "https://mega.nz/#" + (folder != null ? "f" : "") + dec_link;

        } else {
            return link;
        }
    }

    public static HashSet<String> decryptELC(String link, MainPanel main_panel) {

        String elc;

        HashSet<String> links = new HashSet<>();

        if ((elc = findFirstRegex("mega://elc\\?([0-9a-zA-Z,_-]+)", link, 1)) != null) {

            try {

                byte[] elc_byte = UrlBASE642Bin(elc);

                boolean compression;

                if (((int) elc_byte[0] & 0xFF) != 112 && ((int) elc_byte[0] & 0xFF) != 185) {

                    throw new Exception("BAD ELC!");

                } else {

                    compression = (((int) elc_byte[0] & 0xFF) == 112);
                }

                elc_byte = Arrays.copyOfRange(elc_byte, 1, elc_byte.length);

                if (compression) {

                    InputStream is = new GZIPInputStream(new ByteArrayInputStream(elc_byte));

                    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

                        byte[] buffer = new byte[MainPanel.DEFAULT_BYTE_BUFFER_SIZE];

                        int reads;

                        while ((reads = is.read(buffer)) != -1) {

                            out.write(buffer, 0, reads);
                        }

                        elc_byte = out.toByteArray();

                    }
                }

                int bin_links_length = ByteBuffer.wrap(recReverseArray(Arrays.copyOfRange(elc_byte, 0, 4), 0, 3)).getInt();

                byte[] bin_links = Arrays.copyOfRange(elc_byte, 4, 4 + bin_links_length);

                short url_bin_length = ByteBuffer.wrap(recReverseArray(Arrays.copyOfRange(elc_byte, 4 + bin_links_length, 4 + bin_links_length + 2), 0, 1)).getShort();

                byte[] url_bin = Arrays.copyOfRange(elc_byte, 4 + bin_links_length + 2, 4 + bin_links_length + 2 + url_bin_length);

                if (!new String(url_bin).contains("http")) {

                    throw new Exception("BAD ELC HOST URL!");
                }

                short pass_bin_length = ByteBuffer.wrap(recReverseArray(Arrays.copyOfRange(elc_byte, 4 + bin_links_length + 2 + url_bin_length, 4 + bin_links_length + 2 + url_bin_length + 2), 0, 1)).getShort();

                byte[] pass_bin = Arrays.copyOfRange(elc_byte, 4 + bin_links_length + 2 + url_bin_length + 2, 4 + bin_links_length + 2 + url_bin_length + 2 + pass_bin_length);

                URL url = new URL(new String(url_bin).trim());

                HttpURLConnection con = null;

                if (MainPanel.isUse_proxy()) {

                    con = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(MainPanel.getProxy_host(), MainPanel.getProxy_port())));

                    if (MainPanel.getProxy_user() != null && !"".equals(MainPanel.getProxy_user())) {

                        con.setRequestProperty("Proxy-Authorization", "Basic " + MiscTools.Bin2BASE64((MainPanel.getProxy_user() + ":" + MainPanel.getProxy_pass()).getBytes()));
                    }
                } else {

                    con = (HttpURLConnection) url.openConnection();
                }

                con.setRequestMethod("POST");

                con.setDoOutput(true);

                con.setConnectTimeout(Upload.HTTP_TIMEOUT);

                con.setReadTimeout(Upload.HTTP_TIMEOUT);

                con.setRequestProperty("User-Agent", MainPanel.DEFAULT_USER_AGENT);

                HashMap<String, String> elc_account_data;

                String user, api_key;

                boolean remember_master_pass;

                if (main_panel.getElc_accounts().get(url.getHost()) != null) {

                    elc_account_data = (HashMap) main_panel.getElc_accounts().get(url.getHost());

                    if (main_panel.getMaster_pass_hash() != null) {

                        if (main_panel.getMaster_pass() == null) {

                            GetMasterPasswordDialog dialog = new GetMasterPasswordDialog(main_panel.getView(), true, main_panel.getMaster_pass_hash(), main_panel.getMaster_pass_salt(), main_panel);

                            dialog.setLocationRelativeTo(main_panel.getView());

                            dialog.setVisible(true);

                            if (dialog.isPass_ok()) {

                                main_panel.setMaster_pass(dialog.getPass());

                                dialog.deletePass();

                                remember_master_pass = dialog.getRemember_checkbox().isSelected();

                                dialog.dispose();

                                user = new String(CryptTools.aes_cbc_decrypt_pkcs7(BASE642Bin(elc_account_data.get("user")), main_panel.getMaster_pass(), CryptTools.AES_ZERO_IV));

                                api_key = new String(CryptTools.aes_cbc_decrypt_pkcs7(BASE642Bin(elc_account_data.get("apikey")), main_panel.getMaster_pass(), CryptTools.AES_ZERO_IV));

                                if (!remember_master_pass) {

                                    main_panel.setMaster_pass(null);
                                }

                            } else {

                                dialog.dispose();

                                throw new Exception("NO valid ELC account available!");
                            }

                        } else {

                            user = new String(CryptTools.aes_cbc_decrypt_pkcs7(BASE642Bin(elc_account_data.get("user")), main_panel.getMaster_pass(), CryptTools.AES_ZERO_IV));

                            api_key = new String(CryptTools.aes_cbc_decrypt_pkcs7(BASE642Bin(elc_account_data.get("apikey")), main_panel.getMaster_pass(), CryptTools.AES_ZERO_IV));

                        }

                    } else {

                        user = elc_account_data.get("user");

                        api_key = elc_account_data.get("apikey");
                    }

                } else {

                    throw new Exception("NO valid ELC account available!");
                }

                String postdata = "OPERATION_TYPE=D&DATA=" + new String(pass_bin) + "&USER=" + user + "&APIKEY=" + api_key;

                con.getOutputStream().write(postdata.getBytes());

                InputStream is = con.getInputStream();

                try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {

                    byte[] buffer = new byte[MainPanel.DEFAULT_BYTE_BUFFER_SIZE];

                    int reads;

                    while ((reads = is.read(buffer)) != -1) {

                        out.write(buffer, 0, reads);
                    }

                    ObjectMapper objectMapper = new ObjectMapper();

                    HashMap res_map = objectMapper.readValue(new String(out.toByteArray()), HashMap.class);

                    String dec_pass = (String) res_map.get("d");

                    if (dec_pass != null && dec_pass.length() > 0) {

                        dec_pass = (String) res_map.get("d");

                        byte[] pass_dec_byte = BASE642Bin(dec_pass);

                        byte[] key = Arrays.copyOfRange(pass_dec_byte, 0, 16);

                        byte[] iv = new byte[16];

                        Arrays.fill(iv, (byte) 0);

                        System.arraycopy(pass_dec_byte, 16, iv, 0, 8);

                        byte[] bin_links_dec = CryptTools.aes_cbc_decrypt(bin_links, key, iv);

                        String[] links_string = (new String(bin_links_dec).trim()).split("\\|");

                        for (String s : links_string) {

                            links.add("https://mega.nz/" + s);
                        }

                    } else {
                        throw new Exception(url.getAuthority() + " ELC SERVER ERROR " + new String(out.toByteArray()));
                    }

                }

            } catch (Exception ex) {
                Logger.getLogger(CryptTools.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(main_panel.getView(), ex.getMessage(), "ELC ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }

        return links;
    }

    public static HashSet<String> decryptDLC(String data, MainPanel main_panel) {

        HashSet<String> links = new HashSet<>();

        String dlc_url = "http://service.jdownloader.org/dlcrypt/service.php";

        String dlc_rev = "34065";

        String dlc_master_key = "447E787351E60E2C6A96B3964BE0C9BD";

        String dlc_id = data.substring(data.length() - 88);

        String enc_dlc_data = data.substring(0, data.length() - 88).trim();

        try {

            URL url = new URL(dlc_url);

            HttpURLConnection con = null;

            if (MainPanel.isUse_proxy()) {

                con = (HttpURLConnection) url.openConnection(new Proxy(Proxy.Type.HTTP, new InetSocketAddress(MainPanel.getProxy_host(), MainPanel.getProxy_port())));

                if (MainPanel.getProxy_user() != null && !"".equals(MainPanel.getProxy_user())) {

                    con.setRequestProperty("Proxy-Authorization", "Basic " + MiscTools.Bin2BASE64((MainPanel.getProxy_user() + ":" + MainPanel.getProxy_pass()).getBytes()));
                }
            } else {

                con = (HttpURLConnection) url.openConnection();
            }

            con.setRequestMethod("POST");

            con.setDoOutput(true);

            con.setConnectTimeout(Upload.HTTP_TIMEOUT);

            con.setReadTimeout(Upload.HTTP_TIMEOUT);

            con.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux amd64; rv:44.0) Gecko/20100101 Firefox/44.0");

            con.setRequestProperty("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");

            con.setRequestProperty("Accept-Language", "de,en-gb;q=0.7, en;q=0.3");

            con.setRequestProperty("Accept-Encoding", "gzip, deflate");

            con.setRequestProperty("Accept-Charset", "ISO-8859-1,utf-8;q=0.7,*;q=0.7");

            con.setRequestProperty("Cache-Control", "no-cache");

            con.setRequestProperty("rev", dlc_rev);

            String postdata = "destType=jdtc6&b=JD&srcType=dlc&data=" + dlc_id + "&v=" + dlc_rev;

            con.getOutputStream().write(postdata.getBytes());

            InputStream is = con.getInputStream();

            String enc_dlc_key;

            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                byte[] buffer = new byte[MainPanel.DEFAULT_BYTE_BUFFER_SIZE];
                int reads;
                while ((reads = is.read(buffer)) != -1) {

                    out.write(buffer, 0, reads);
                }
                enc_dlc_key = findFirstRegex("< *rc *>(.+?)< */ *rc *>", new String(out.toByteArray()), 1);
            }

            String dec_dlc_key = new String(CryptTools.aes_ecb_decrypt(BASE642Bin(enc_dlc_key), hex2bin(dlc_master_key))).trim();

            String dec_dlc_data = new String(CryptTools.aes_cbc_decrypt(BASE642Bin(enc_dlc_data), BASE642Bin(dec_dlc_key), BASE642Bin(dec_dlc_key))).trim();

            String dec_dlc_data_file = findFirstRegex("< *file *>(.+?)< */ *file *>", new String(BASE642Bin(dec_dlc_data), "UTF-8"), 1);

            ArrayList<String> urls = findAllRegex("< *url *>(.+?)< */ *url *>", dec_dlc_data_file, 1);

            for (String s : urls) {

                links.add(new String(BASE642Bin(s)));
            }

        } catch (Exception ex) {

            Logger.getLogger(CryptTools.class.getName()).log(Level.SEVERE, null, ex);

            JOptionPane.showMessageDialog(main_panel.getView(), ex.getMessage(), "DLC ERROR", JOptionPane.ERROR_MESSAGE);
        }

        return links;
    }

    public static String MEGAUserHash(byte[] str, int[] aeskey) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, Exception {

        int[] s32 = bin2i32a(str);

        int[] h32 = {0, 0, 0, 0};

        int[] iv = {0, 0, 0, 0};

        for (int i = 0; i < s32.length; i++) {

            h32[i % 4] ^= s32[i];
        }

        for (int i = 0; i < 0x4000; i++) {

            h32 = CryptTools.aes_cbc_encrypt_ia32(h32, aeskey, iv);
        }

        int[] res = {h32[0], h32[2]};

        return Bin2UrlBASE64(i32a2bin(res));
    }

    public static int[] MEGAPrepareMasterKey(int[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        int[] pkey = {0x93C467E3, 0x7DB0_C7A4, 0xD1BE_3F81, 0x0152_CB56};

        int[] iv = {0, 0, 0, 0};

        for (int r = 0; r < 0x10000; r++) {

            for (int j = 0; j < key.length; j += 4) {

                int[] k = {0, 0, 0, 0};

                for (int i = 0; i < 4; i++) {

                    if (i + j < key.length) {

                        k[i] = key[i + j];
                    }
                }

                pkey = CryptTools.aes_cbc_encrypt_ia32(pkey, k, iv);
            }
        }

        return pkey;
    }

    public static byte[] PBKDF2HMACSHA256(String password, byte[] salt, int iterations) throws NoSuchAlgorithmException, InvalidKeySpecException {

        SecretKeyFactory f = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");

        KeySpec ks = new PBEKeySpec(password.toCharArray(), salt, iterations, 256);

        return f.generateSecret(ks).getEncoded();
    }

    private CryptTools() {
    }
}
