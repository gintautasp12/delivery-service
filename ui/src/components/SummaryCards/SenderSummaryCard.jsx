import React from "react";
import { Grid } from "@material-ui/core";

export default function SenderSummaryCard(props) {
  return (
    <div className="form-wrapper summary-subform-wrapper">
      <div className="form-inner form-inner-summary">
        <div className="form-header summary-subheader">Sender Info</div>
        <Grid container>
          <Grid item xs={12}>
            <ul className="summary-ul">
              <li>
                {props.sender.name} {props.sender.surname}
              </li>
              <li>
                {props.sender.address}, {props.sender.city}
              </li>
              <li>Phone Number: {props.sender.number}</li>
              <li>
                Pick Up Date:{" "}
                {props.pickUpDate.toLocaleString("en-US", {
                  year: "numeric",
                  month: "2-digit",
                  day: "2-digit",
                  hour: "2-digit",
                  minute: "2-digit",
                  hour12: false,
                })}
              </li>
            </ul>
          </Grid>
        </Grid>
      </div>
    </div>
  );
}