import axios from "axios";

export const addProjectTask = (backlog_id, project_task, history) => async (
  dispatch
) => {
  await axios.post(`/api/backlog/${backlog_id}`, project_task);
  history.pushState(`/projectBoard/${backlog_id}`);
};
