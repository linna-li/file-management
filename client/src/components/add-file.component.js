import React, {useState, useEffect} from "react";
import UploadService from "../services/file.service";

const UploadFiles = () => {
  const [selectedFiles, setSelectedFiles] = useState(undefined);
  const [setCurrentFile] = useState(undefined);
  const [message, setMessage] = useState("");
  const [fileInfos, setFileInfos] = useState([]);

  useEffect(() => {
    UploadService.getAll().then((response) => {
      setFileInfos(response.data);
    });
  }, []);

  const selectFile = (event) => {
    setSelectedFiles(event.target.files);
  };

  const setfileInfos = (event) => {
    UploadService.getAll().then((response) => {
      setFileInfos(response.data);
    });
  };

  const upload = () => {
    let currentFile = selectedFiles[0];

    UploadService.upload(currentFile,(event) => {})
                 .then((response) => {
                   setMessage(response.data.message);
                   return UploadService.getAll();
                 })
                 .then((files) => {
                   setFileInfos(files.data);
                 })
                 .catch(() => {
                   setMessage("Could not upload the file!");
                 });

    setSelectedFiles(undefined);
  };

  function deleteFile(id) {
    UploadService.delete(id)
                 .then((response) => {
                   setMessage(response.data.message);
                   return UploadService.getAll();
                 })
                 .then((files) => {
                   setFileInfos(files.data);
                 })
                 .catch(() => {
                   setMessage("Could not delete the file!");
                 });
  }

  return (
    <div>
      <div className="row justify-content-start">
        <label className="btn btn-default">
          <input type="file" onChange={selectFile} />
        </label>

        <button
          className="btn btn-success"
          disabled={!selectedFiles}
          onClick={upload}
        >
          Upload
        </button>
      </div>

      <div className="alert alert-light" role="alert">
        {message}
      </div>

      <div>
        <button className="btn btn-primary" onClick={setfileInfos}>
          List All files
        </button>
      </div>

      <div className="container mt-5">
        <div className="card-header">List of Files</div>
        <ul className="list-group list-group-flush">
          {fileInfos &&
           fileInfos.map((file, index) => (
             <li className="list-group-item" key={index}>
               <div className="row">
                 <div className="col-4">
                   {file.id}
                 </div>
                 <div className="col-4">
                   {file.fileName}
                 </div>
                 <div className="col-4">
                   <button type="button" className="btn btn-danger"
                           onClick={() => { deleteFile(file.id) }}>
                     Delete</button>
                 </div>
               </div>
             </li>
           ))}
        </ul>
      </div>
    </div>
  );
};

export default UploadFiles;