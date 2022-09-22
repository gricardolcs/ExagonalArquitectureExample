export function fileToBase64(file) {
  return new Promise((resolve, reject) => {
    if (!file) {
      reject('Error File not found');
    }

    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onload = () => {
      resolve(reader.result.replace('data:', '').replace(/^.+,/, ''));
    };
  });
}

const FileToString = {
  fileToBase64,
};

export default FileToString;
