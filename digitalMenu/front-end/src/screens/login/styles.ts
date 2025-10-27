import { Dimensions, StyleSheet } from "react-native";
import { theme } from "../../theme/theme";

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: theme.colors.background,
    padding: theme.spacing.md,
    justifyContent: "center",
  },

  boxTop: {
    flex: 1,
    alignItems: "center",
    justifyContent: "center",
  },

  title: {
    fontSize: 28,
    fontWeight: "bold",
    color: theme.colors.textPrimary,
    marginBottom: theme.spacing.lg,
    marginTop: theme.spacing.lg,
  },

  boxMid: {
    paddingHorizontal: theme.spacing.md,
  },

  boxBottom: {
    width: "100%",
    alignItems: "center",
    marginTop: theme.spacing.lg,
  },

  logo: {
    width: 120,
    height: 120,
    resizeMode: "contain",
  },

  titleInput: {
    marginLeft: theme.spacing.md,
    color: theme.colors.textPrimary,
  },

  boxInput: {
    flexDirection: "row",
    alignItems: "center",
    borderWidth: 1,
    borderRadius: theme.radius.lg,
    marginTop: theme.spacing.sm,
    marginBottom: theme.spacing.md,
    paddingHorizontal: theme.spacing.md,
    backgroundColor: theme.colors.surface,
    borderColor: theme.colors.border,
  },

  input: {
    flex: 1,
    height: 40,
    marginLeft: theme.spacing.sm,
    color: theme.colors.textPrimary,
    fontSize: 16,
  },

  textMid: {
    fontSize: 16,
    marginTop: theme.spacing.sm,
    marginBottom: theme.spacing.lg,
    color: theme.colors.textSecondary,
    textDecorationLine: "underline",
    fontWeight: "bold",
    alignSelf: "flex-end",
    paddingRight: theme.spacing.md,
  },

  button: {
    width: 200,
    height: 50,
    alignItems: "center",
    justifyContent: "center",
    backgroundColor: theme.colors.primary,
    borderRadius: theme.radius.lg,
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 4 },
    shadowOpacity: 0.2,
    shadowRadius: 6,
    elevation: 6,
  },

  buttonText: {
    fontSize: 18,
    fontWeight: "bold",
    color: theme.colors.background,
  },

  textBottom: {
    fontSize: 16,
    color: theme.colors.textSecondary,
  },

  bottomTextContainer: {
    flexDirection: "row",
    justifyContent: "center",
    marginTop: theme.spacing.md,
  },

  textBottomLink: {
    fontSize: 16,
    color: theme.colors.primary,
    fontWeight: "bold",
  },
});
