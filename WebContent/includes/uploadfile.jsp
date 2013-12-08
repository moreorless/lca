<%@ page contentType="text/html;charset=utf-8" language="java"%>
<div id="upload-modal" class="modal hide fade" style="padding:10px;" tabindex="-1"
		role="dialog" aria-labelledby="uploadModalLabel" aria-hidden="true">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-hidden="true">×</button>
			<h3 id="uploadModalLabel">上传源数据</h3>
		</div>

		<IFRAME name="fm1" id="fm1" src="#" style="display: none;"> </IFRAME>
		<IFRAME name="fm2" id="fm2" src="#" style="display: none;"> </IFRAME>
		<IFRAME name="fm3" id="fm3" src="#" style="display: none;"> </IFRAME>

		<div class="modal-body">

			<div class="control-group">
				<form target="fm1" id="formUploadElec" name="form1"
					action="${base}/cycle/uploadDataFiles?cycletype=electric" enctype="multipart/form-data"
					method="post">
					<b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;发电周期源数据</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="file" name="FileElec" id="FileElec" size="30"
						UNSELECTABLE="on" />
					<input type="submit" class="btn btn-small btn-info" name="upElecSubmit"
						value="上传" onclick="alert('上传成功！')"/>
				</form>

				<form target="fm2" id="formUploadTransport" name="form2"
					action="${base}/cycle/uploadDataFiles?cycletype=transport" enctype="multipart/form-data"
					method="post">
					<b>交通燃料周期源数据</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="file" name="FileTransport" id="FileTransport" size="30"
						UNSELECTABLE="on" " />
					<input type="submit" class="btn btn-small btn-info" name="upTransportSubmit"
						value="上传" onclick="alert('上传成功！')"/>
				</form>
				<form target="fm3" id="formUploadGas" name="form3"
					action="${base}/cycle/uploadDataFiles?cycletype=gas" enctype="multipart/form-data"
					method="post">
					<b>天然气产业链源数据</b>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <input
						type="file" name="FileGas" id="FileGas" size="30"
						UNSELECTABLE="on"/>
					<input type="submit" class="btn btn-small btn-info" name="upGasSubmit"
						value="上传" onclick="alert('上传成功！')"/>
				</form>
			</div>
		</div>
	</div>