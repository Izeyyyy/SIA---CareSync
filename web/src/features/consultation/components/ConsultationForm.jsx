import { useState, useEffect } from "react";

import FormSection from "../../../components/common/FormSection";
import FormField from "../../../components/common/FormField";

import PrimaryButton from "../../../components/common/PrimaryButton";
import SecondaryButton from "../../../components/common/SecondaryButton";

export default function ConsultationForm({

    initialData = {},

    onSubmit,

    onCancel,

    loading = false

}) {

    const [formData, setFormData] = useState({

        chiefComplaint:
            initialData.chiefComplaint || "",

        diagnosis:
            initialData.diagnosis || "",

        treatmentPlan:
            initialData.treatmentPlan || "",

        prescription:
            initialData.prescription || "",

        notes:
            initialData.notes || ""

    });

    function handleChange(e) {

        const { name, value } = e.target;

        setFormData(previous => ({

            ...previous,

            [name]: value

        }));

    }

    function handleSubmit(e) {

        e.preventDefault();

        onSubmit(formData);

    }

    return (

        <form onSubmit={handleSubmit}>

            <FormSection title="Consultation Information">

                <FormField
                    label="Chief Complaint"
                >

                    <textarea

                        name="chiefComplaint"

                        value={formData.chiefComplaint}

                        onChange={handleChange}

                        rows={3}

                        required

                    />

                </FormField>

                <FormField
                    label="Diagnosis"
                >

                    <textarea

                        name="diagnosis"

                        value={formData.diagnosis}

                        onChange={handleChange}

                        rows={4}

                        required

                    />

                </FormField>

                <FormField
                    label="Treatment Plan"
                >

                    <textarea

                        name="treatmentPlan"

                        value={formData.treatmentPlan}

                        onChange={handleChange}

                        rows={4}

                    />

                </FormField>

                <FormField
                    label="Prescription"
                >

                    <textarea

                        name="prescription"

                        value={formData.prescription}

                        onChange={handleChange}

                        rows={4}

                    />

                </FormField>

                <FormField
                    label="Additional Notes"
                >

                    <textarea

                        name="notes"

                        value={formData.notes}

                        onChange={handleChange}

                        rows={5}

                    />

                </FormField>

            </FormSection>

            <div
                style={{
                    display: "flex",
                    gap: "10px",
                    marginTop: "20px"
                }}
            >

                <PrimaryButton
                    type="submit"
                    disabled={loading}
                >

                    {loading
                        ? "Saving..."
                        : "Save Consultation"}

                </PrimaryButton>

                <SecondaryButton
                    type="button"
                    onClick={onCancel}
                >

                    Cancel

                </SecondaryButton>

            </div>

        </form>

    );

}