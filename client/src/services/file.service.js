import http from "../http-common";

class FileDataService {
  getAll() {
    return http.get("/files")
               .catch(e => {
                 console.log(e);
               });
  }

  get(id) {
    return http.get(`/file/${id}`);
  }

  findByTitleContaining(inputText) {
    return http.get(`/files/${inputText}`);
  }

  create(fileName, data) {
    return http.post(`/file/${fileName}`, data);
  }

  deleteByFileName(fileName) {
    return http.delete(`/files/${fileName}`);
  }

  delete(id) {
    return http.delete(`/file/${id}`);
  }

  upload(file) {
    let fileData = new FormData();
    fileData.append("file", file);
    return http.post(`/file`, fileData, {
      headers: {
        "Content-Type": "multipart/form-data; boundary=----------287032381131322",
      }
    });
  }
}

export default new FileDataService();
