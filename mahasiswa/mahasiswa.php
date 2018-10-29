<?php
    header("Content-type:application/json");
    include'koneksi.php';
    $query = "select * from tb_mahasiswa";
    $hasil = mysql_query($query);

    if (mysql_num_rows($hasil)>0) {
      $response = array();
      $msg['code'] = 200;
      $msg['message'] = "Success";
      $response["meta"] = $msg;
      $response["data"] = array();

      while ($rows = mysql_fetch_array($hasil)) {
        $h['id_mhs'] = utf8_encode($rows['id_mhs']);
        $h['name'] = utf8_encode($rows['name']);
        $h['address'] = utf8_encode($rows['address']);
        $h['phone'] = utf8_encode($rows['phone']);

        array_push($response['data'], $h);
      }
      echo json_encode($response);
    }else{
      $msg['code'] = 404;
      $msg['message'] = "Data not found";
      $response["meta"] = $msg;
      echo json_encode($response);
    }
 ?>
