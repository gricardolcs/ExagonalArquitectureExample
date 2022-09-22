import { convertBase64ToFile } from '../../components/molecules/stageInformation/helpers/Convert';

export const downloadFileUtils = (file) => {
  const { fileName, report } = file;
  const fileToDownload = convertBase64ToFile(report);
  const url = window.URL.createObjectURL(new Blob([fileToDownload]));
  const link = document.createElement('a');
  link.href = url;
  link.setAttribute('download', fileName);
  document.body.appendChild(link);
  link.click();
};
